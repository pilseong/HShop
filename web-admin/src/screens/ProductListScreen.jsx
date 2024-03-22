import React from 'react'
import { Alert, Badge, Button, Col, Form, Image, Modal, Pagination, Row, Table } from 'react-bootstrap'
import { useEffect, useState } from 'react'
import { RiCheckboxCircleFill } from "react-icons/ri";
import { TbMinusVertical } from "react-icons/tb";
import { FaEdit } from "react-icons/fa";
import { FaPortrait } from "react-icons/fa";
import { MdDelete } from "react-icons/md";
import { BiBadge, BiSolidUpArrow } from "react-icons/bi";
import { BiSolidDownArrow } from "react-icons/bi";
import axios from 'axios'
import { LinkContainer } from 'react-router-bootstrap';
import Message from '../util/Message'
import { Link } from 'react-router-dom';
import { useSearchProductsMutation } from '../slices/productsApiSlice';
import Loader from '../components/Loader';


function ProductListScreen() {

    const [show, setShow] = useState(false);
    const [targetProduct, setTargetProduct] = useState({})
    const [message, setMessage] = useState("")
    const [currentPage, setCurrentPage] = useState(0)
    const [lastPage, setLastPage] = useState(0)
    const [orderBy, setOrderBy] = useState('asc')
    const [sortBy, setSortBy] = useState('name')
    const [keyword, setKeyword] = useState("")
    const [cleared, setCleared] = useState(0)


    // 유저리스트
    const [products, setProducts] = useState([])
    const [error, setError] = useState({
        code: "",
        message: ""
    })

    const [searchProducts, { isLoading: isProductLoading }] = useSearchProductsMutation()

    // clear 버튼을 눌렀을 때 keyword가 클리어 된 후에 실행되도록 추가
    useEffect(() => {
        if (cleared > 0)
            fetchProducts(0, 'asc', 'name')
    }, [cleared])

    useEffect(() => {
        fetchProducts(0)
    }, [])

    const fetchProducts = async (page = 0, order = 'asc', sort = 'name') => {
        try {
            const { data } = await searchProducts({
                query: keyword,
                pageNo: page,
                pageSize: 10,
                sortBy: sort,
                orderBy: order
            })
            // const { data } = await axios.get(
            //     `http://localhost:8080/catalog-service/api/products`, {
            //     params: {
            //         query: keyword,
            //         pageNo: page,
            //         pageSize: 10,
            //         sortBy: sort,
            //         orderBy: order
            //     }
            // }, {
            //     headers: {
            //         "Content-Type": "application/json"
            //     },
            //     withCredentials: true
            // });

            setProducts(data.content)
            setCurrentPage(data.pageNo)
            setLastPage(data.totalPages)
            setOrderBy(order)
            setSortBy(sort)

        } catch (error) {
            console.log(error)
        }
    }

    const exportToCSV = async () => {
        try {
            const result = await axios.post(
                `/api/products/export/csv?pageNo=${currentPage}&pageSize=10&sortBy=${sortBy}&orderBy=${orderBy}`, {
                userId: "9b93b0f0-7a18-4e8a-9b2e-cb501e7394a3",
                keyword: `${keyword}`
            }, {
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "text/csv"
                }
            });


            // cvs를 받아서 바로 다운로드 하는 기능
            const filename = result.headers
                .get("Content-Disposition")
                .split('=')[1]

            console.log(filename)

            const url = window.URL.createObjectURL(
                new Blob([result.data]),
            );

            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', `${filename}`);

            // Append to html link element page
            document.body.appendChild(link);

            // Start download
            link.click();

            // Clean up and remove the link
            link.parentNode.removeChild(link);

        } catch (error) {
            console.log(error)
        }
    }

    const handleModalClose = () => {
        setShow(false)
    }

    const deleteUserHandler = async () => {
        try {
            await axios.delete(`/api/products/${targetProduct.id}`)
            await fetchProducts(currentPage, orderBy, sortBy)

            showMessage(`user with ${targetProduct.name} has been deleted successfully`)

        } catch (error) {
            console.log(error)
            setError(error.response.data)
        }
        setShow(false);
    }

    const handleModalShow = () => setShow(true);


    // 수정 실행 메소드
    const submitEdition = async (product) => {
        try {
            // 데이터 생성
            const formData = new FormData();
            formData.append("command", new Blob([JSON.stringify(product)], {
                type: "application/json",
            }));

            const result = await axios.post('/api/products/edit', formData, {
                headers: {
                    "Content-Type": "multipart/form-data",
                }
            })
            console.log(result)
            showMessage(`product with ${product.name} has been upated successfully`)
            await fetchProducts(currentPage, orderBy, sortBy)
        } catch (error) {
            console.log(error)
            setError(error.response.data)
            return
        }
    }

    const handleActiveToggle = async (product) => {

        // 서버에 변경, 패스워드 공백은 검증로직 예외
        await submitEdition({
            ...product,
            password: '',
            status: product.status === 'ACTIVE' ? 'INACTIVE' : 'ACTIVE',
            roles: product.roles.map(r => r.id)
        })

        //     // 목록 업데이트
        //     // let updatedproducts = products.filter(u => u.id !== product.id)
        //     // updatedproducts.push({ ...product, enabled: !product.enabled })

        //     // setProducts(updatedProducts)
    }

    const navigatePage = (page) => {
        fetchProducts(page, orderBy, sortBy)
    }

    const showMessage = (msg) => {
        setMessage(msg)

        setTimeout(() => {
            setMessage("");
        }, 3000);

    }
    return (
        <>
            {
                isProductLoading ? (<Loader></Loader>) : (
                    <>
                        <h1 className='mb-5'>Product Lists</h1>
                        <Row className='d-flex mb-2' md={3}>
                            <Form.Group as={Col}>
                                <Form.Control
                                    type="search"
                                    aria-describedby="search input field"
                                    placeholder='Enter keyword to search'
                                    onKeyDown={e => {
                                        if (e.key === 'Enter') {
                                            fetchProducts(0, 'asc', 'name')
                                        }
                                    }}
                                    onChange={e => {
                                        setKeyword(e.target.value)
                                    }}
                                    value={keyword}
                                />
                            </Form.Group>
                            <Col className='d-flex me-auto' md={1}>
                                <Button onClick={() =>
                                    fetchProducts(0, 'asc', 'name')}
                                >Search</Button>
                                <Button className='ms-2' variant='secondary'
                                    onClick={() => {
                                        setKeyword('')
                                        setCleared(cleared + 1)
                                    }
                                    }>Clear</Button>
                            </Col>
                            <Col className='d-flex me-auto' md={2}>
                                <Button className='ms-auto' onClick={
                                    () => exportToCSV()
                                }>CSV</Button>
                            </Col>
                            <Col className='ms-auto d-flex'>
                                <Link className='ms-auto mb-2' to="/products/new">
                                    <Button variant='secondary'>Create Product</Button>
                                </Link>
                            </Col>
                        </Row >
                        {
                            error?.code && (
                                <Message variant='danger' className='mt-2'>
                                    <Form.Text className="text-muted">
                                        {
                                            error.message
                                                .split(',')
                                                .map(err => <><strong>{err}</strong><br /></>)
                                        }
                                    </Form.Text>
                                </Message>
                            )
                        }
                        {
                            message.length > 0 && (
                                <Alert variant="info">{message}</Alert>
                            )
                        }
                        {
                            products.length === 0 ? (
                                <Row className='text-center mt-5'>
                                    <h4>No Product Found</h4>
                                </Row>
                            ) : (
                                <Row>
                                    <Table striped bordered hover>
                                        <thead>
                                            <tr>
                                                <th>Product ID</th>
                                                <th>Main image</th>
                                                <th>
                                                    <a type="button"
                                                        onClick={() => fetchProducts(currentPage, orderBy, 'name')}
                                                    >Product Name</a>
                                                    {
                                                        sortBy === 'name' && (
                                                            orderBy === 'asc' ?
                                                                (<BiSolidDownArrow
                                                                    type='button'
                                                                    onClick={() => { fetchProducts(currentPage, 'desc', 'name') }}
                                                                />) :
                                                                (<BiSolidUpArrow
                                                                    type='button'
                                                                    onClick={() => { fetchProducts(currentPage, 'asc', 'name') }}
                                                                />)
                                                        )
                                                    }
                                                </th>
                                                <th>Brand</th>
                                                <th>Category</th>
                                                <th>Stock</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {
                                                products.map(product => (
                                                    <tr key={product.id} className='align-middle'>
                                                        <td><small>{product.id}</small></td>
                                                        <td>
                                                            {
                                                                product.mainImage &&
                                                                (
                                                                    <Image width={120} thumbnail fluid
                                                                        src={`http://localhost:8080/catalog-service/photos/productimages/${product.id}/${product.mainImage}`} />
                                                                )
                                                            }
                                                            {
                                                                !product.mainImage && (
                                                                    <FaPortrait className='icon-sliver' size={120} />
                                                                )
                                                            }
                                                        </td>
                                                        <td>{product.shortName}</td>
                                                        <td>{product.brand.name}</td>
                                                        <td>{product.category.name}</td>
                                                        <td>{product.inventory.amount}</td>
                                                        <td>
                                                            <LinkContainer to={`/products/${product.id}`}>
                                                                <Button variant="seconary"><FaEdit size={24} /></Button>
                                                            </LinkContainer>
                                                            <TbMinusVertical />
                                                            <Button variant="seconary"
                                                                onClick={() => {
                                                                    handleModalShow()
                                                                    setTargetProduct(product)
                                                                }}
                                                            ><MdDelete size={24} />
                                                            </Button>
                                                        </td>
                                                    </tr>
                                                ))
                                            }
                                        </tbody>
                                    </Table>
                                </Row >
                            )
                        }
                        <Pagination className='d-flex justify-content-center'>
                            {lastPage > 5 && (
                                <Pagination.First />
                            )}

                            {currentPage != 0 && (
                                <Pagination.Prev
                                    onClick={() => navigatePage(currentPage - 1)}
                                />
                            )}
                            {
                                (lastPage <= 5 || currentPage <= 2) &&
                                [...Array(lastPage > 5 ? 5 : lastPage).keys()]
                                    .map(index => (
                                        <Pagination.Item
                                            active={index === currentPage}
                                            onClick={() => navigatePage(index)}
                                        >{index + 1}</Pagination.Item>
                                    ))
                            }

                            {
                                (lastPage > 5 && currentPage > 2 && lastPage - currentPage > 3) &&
                                [...Array(5).keys()]
                                    .map(index => (
                                        <Pagination.Item
                                            active={index + currentPage - 2 === currentPage}
                                            style={{
                                                style: "none"
                                            }}
                                            onClick={() => {
                                                navigatePage(index + currentPage - 2)
                                            }}
                                        >{index + currentPage + 1 - 2}</Pagination.Item>
                                    ))
                            }

                            {
                                (lastPage > 5 && lastPage - currentPage <= 3) &&
                                [...Array(5).keys()]
                                    .map(index => (
                                        <Pagination.Item
                                            active={lastPage - 5 + index === currentPage}
                                            onClick={() => navigatePage(lastPage - 5 + index)}
                                        >{lastPage - 5 + index + 1}</Pagination.Item>
                                    ))
                            }

                            {currentPage < lastPage - 1 &&

                                <Pagination.Next
                                    onClick={() => navigatePage(currentPage + 1)}
                                />
                            }
                            {lastPage > 5 && (
                                <Pagination.Last />
                            )}
                        </Pagination>
                        <Modal show={show} onHide={handleModalClose}>
                            <Modal.Header closeButton>
                                <Modal.Title>Delete</Modal.Title>
                            </Modal.Header>
                            <Modal.Body>Do you want to Delete a user with {targetProduct.name}</Modal.Body>
                            <Modal.Footer>
                                <Button variant="secondary" onClick={handleModalClose}>
                                    Close
                                </Button>
                                <Button variant="primary" onClick={deleteUserHandler}>
                                    Delete User
                                </Button>
                            </Modal.Footer>
                        </Modal>
                    </>
                )
            }
        </>
    )
}

export default ProductListScreen