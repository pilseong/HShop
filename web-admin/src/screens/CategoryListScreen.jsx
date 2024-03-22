import React from 'react'
import { Alert, Button, Col, Form, Image, Modal, Pagination, Row, Table } from 'react-bootstrap'
import { useEffect, useState } from 'react'
import { RiCheckboxCircleFill } from "react-icons/ri";
import { TbMinusVertical } from "react-icons/tb";
import { FaEdit } from "react-icons/fa";
import { FaPortrait } from "react-icons/fa";
import { MdDelete } from "react-icons/md";
import { BiSolidUpArrow } from "react-icons/bi";
import { BiSolidDownArrow } from "react-icons/bi";
import axios from 'axios'
import { LinkContainer } from 'react-router-bootstrap';
import Message from '../util/Message'
import { Link } from 'react-router-dom';


function CategoryListScreen() {

    const [show, setShow] = useState(false);
    const [targetUser, setTargetUser] = useState({})
    const [message, setMessage] = useState("")
    const [currentPage, setCurrentPage] = useState(0)
    const [lastPage, setLastPage] = useState(0)
    const [orderBy, setOrderBy] = useState('asc')
    const [sortBy, setSortBy] = useState('name')
    const [keyword, setKeyword] = useState("")
    const [cleared, setCleared] = useState(0)


    // 유저리스트
    const [categories, setCategories] = useState([])
    const [error, setError] = useState({
        code: "",
        message: ""
    })

    // clear 버튼을 눌렀을 때 keyword가 클리어 된 후에 실행되도록 추가
    useEffect(() => {
        if (cleared > 0)
            fetchCategories(0, 'asc', 'name')
    }, [cleared])

    useEffect(() => {
        fetchCategories(0)
    }, [])

    const fetchCategories = async (page = 0, order = 'asc', sort = 'name') => {
        try {
            const { data } = await axios.get(
                `http://localhost:8080/catalog-service/api/categories`, {
                params: {
                    keyword: keyword,
                    pageNo: page,
                    pageSize: 10,
                    sortBy: sort,
                    orderBy: order
                }
            }, {
                headers: {
                    "Content-Type": "application/json"
                },
                withCredentials: true
            });
            console.log(data)
            setCategories(data.content)
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
                `/api/categories/export/csv?pageNo=${currentPage}&pageSize=10&sortBy=${sortBy}&orderBy=${orderBy}`, {
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
            await axios.delete(`/api/categories/${targetUser.id}`)
            await fetchCategories(currentPage, orderBy, sortBy)

            showMessage(`user with ${targetUser.name} has been deleted successfully`)

        } catch (error) {
            console.log(error)
            setError(error.response.data)
        }
        setShow(false);
    }

    const handleModalShow = () => setShow(true);


    // 수정 실행 메소드
    const submitEdition = async (user) => {
        try {
            // 데이터 생성
            const formData = new FormData();
            formData.append("command", new Blob([JSON.stringify(user)], {
                type: "application/json",
            }));

            const result = await axios.post('/api/categories/edit', formData, {
                headers: {
                    "Content-Type": "multipart/form-data",
                }
            })
            console.log(result)
            showMessage(`user with ${user.name} has been upated successfully`)
            await fetchCategories(currentPage, orderBy, sortBy)
        } catch (error) {
            console.log(error)
            setError(error.response.data)
            return
        }
    }

    const handleActiveToggle = async (user) => {

        // 서버에 변경, 패스워드 공백은 검증로직 예외
        await submitEdition({
            ...user,
            password: '',
            status: user.status === 'ACTIVE' ? 'INACTIVE' : 'ACTIVE',
            roles: user.roles.map(r => r.id)
        })

        //     // 목록 업데이트
        //     // let updatedcategories = categories.filter(u => u.id !== user.id)
        //     // updatedcategories.push({ ...user, enabled: !user.enabled })

        //     // setcategories(updatedcategories)
    }

    const navigatePage = (page) => {
        fetchCategories(page, orderBy, sortBy)
    }

    const showMessage = (msg) => {
        setMessage(msg)

        setTimeout(() => {
            setMessage("");
        }, 3000);

    }
    return (
        <>
            <h1 className='mb-5'>Category Lists</h1>
            <Row className='d-flex mb-2' md={3}>
                <Form.Group as={Col}>
                    <Form.Control
                        type="search"
                        aria-describedby="search input field"
                        placeholder='Enter keyword to search'
                        onKeyDown={e => {
                            if (e.key === 'Enter') {
                                fetchCategories(0, 'asc', 'name')
                            }
                        }}
                        onChange={e => {
                            setKeyword(e.target.value)
                        }}
                        value={keyword}
                    />
                </Form.Group>
                <Col className='d-flex me-auto' md={1}>
                    <Button onClick={() => fetchCategories(0, 'asc', 'name')}>Search</Button>
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
                    <Link className='ms-auto mb-2' to="/categories/new">
                        <Button variant='secondary'>Create Category</Button>
                    </Link>
                </Col>
            </Row >
            {
                error?.code && (
                    <Message variant='danger' className='mt-2'>
                        <Form.Text className="text-muted">
                            {
                                error.message.split(',').map(err => <><strong>{err}</strong><br /></>)
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
                categories.length === 0 ? (
                    <Row className='text-center mt-5'>
                        <h4>No User Found</h4>
                    </Row>
                ) : (
                    <Row>
                        <Table striped bordered hover>
                            <thead>
                                <tr>
                                    <th>UserID</th>
                                    <th>Image</th>
                                    <th>
                                        <a type="button"
                                            onClick={() => fetchCategories(currentPage, orderBy, 'name')}
                                        >Category Name</a>
                                        {
                                            sortBy === 'name' && (
                                                orderBy === 'asc' ?
                                                    (<BiSolidDownArrow
                                                        type='button'
                                                        onClick={() => { fetchCategories(currentPage, 'desc', 'name') }}
                                                    />) :
                                                    (<BiSolidUpArrow
                                                        type='button'
                                                        onClick={() => { fetchCategories(currentPage, 'asc', 'name') }}
                                                    />)
                                            )
                                        }
                                    </th>
                                    <th>
                                        <a type="button"
                                            onClick={() => fetchCategories(currentPage, orderBy, 'firstName')}
                                        >Alias</a>
                                        {
                                            sortBy === 'firstName' && (
                                                orderBy === 'asc' ?
                                                    (<BiSolidDownArrow
                                                        type='button'
                                                        onClick={() => { fetchCategories(currentPage, 'desc', 'firstName') }}
                                                    />) :
                                                    (<BiSolidUpArrow
                                                        type='button'
                                                        onClick={() => { fetchCategories(currentPage, 'asc', 'firstName') }}
                                                    />)
                                            )
                                        }
                                    </th>
                                    <th>Status</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    categories.map(user => (
                                        <tr key={user.id} className='align-middle'>
                                            <td><small>{user.id}</small></td>
                                            <td>
                                                {
                                                    user.image &&
                                                    (
                                                        <Image width={120}
                                                            thumbnail
                                                            fluid
                                                            src={`http://localhost:8080/catalog-service/photos/categories/${user.id}/${user.image}`} />
                                                    )
                                                }
                                                {
                                                    !user.image && (
                                                        <FaPortrait className='icon-sliver' size={120} />
                                                    )
                                                }
                                            </td>
                                            <td>{user.name}</td>
                                            <td>{user.alias}</td>
                                            <td>{user.lastName}</td>
                                            <td>
                                                <RiCheckboxCircleFill
                                                    size={24}
                                                    color={user.status === 'ACTIVE' ? "green" : "gray"}
                                                    onClick={() => handleActiveToggle(user)}
                                                />
                                            </td>
                                            <td>
                                                <LinkContainer to={`/categories/${user.id}`}>
                                                    <Button variant="seconary"><FaEdit size={24} /></Button>
                                                </LinkContainer>
                                                <TbMinusVertical />
                                                <Button variant="seconary"
                                                    onClick={() => {
                                                        handleModalShow()
                                                        setTargetUser(user)
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
                <Modal.Body>Do you want to Delete a user with {targetUser.name}</Modal.Body>
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

export default CategoryListScreen