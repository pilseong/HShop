import React from 'react'
import { Alert, Button, Col, Form, Image, Modal, Pagination, Row, Table } from 'react-bootstrap'
import { useEffect, useState } from 'react'
import { RiCheckboxCircleFill } from "react-icons/ri";
import { RiCheckboxBlankCircleLine } from "react-icons/ri";
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
import { useSelector } from 'react-redux';
import { useGetCustomersMutation } from '../slices/customersApiSlice';


function UserListScreen() {

    const [show, setShow] = useState(false);
    const [targetCustomer, setTargetCustomer] = useState({})
    const [message, setMessage] = useState("")
    const [currentPage, setCurrentPage] = useState(0)
    const [lastPage, setLastPage] = useState(0)
    const [orderBy, setOrderBy] = useState('asc')
    const [sortBy, setSortBy] = useState('email')
    const [keyword, setKeyword] = useState("")
    const [cleared, setCleared] = useState(0)

    const { userInfo } = useSelector(state => state.auth)

    // 유저리스트
    const [customers, setCustomers] = useState([])
    const [error, setError] = useState({
        code: "",
        message: ""
    })

    // clear 버튼을 눌렀을 때 keyword가 클리어 된 후에 실행되도록 추가
    useEffect(() => {
        if (cleared > 0)
            fetchCustomers(0, 'asc', 'email')
    }, [cleared])

    useEffect(() => {
        fetchCustomers(0)
    }, [])

    const [getCustomers, { isLoading }] = useGetCustomersMutation()


    const server_url = import.meta.env.VITE_BASE_URL


    const fetchCustomers = async (page = 0, order = 'asc', sort = 'email') => {
        try {
            const response = await getCustomers({
                keyword: keyword,
                pageNo: page,
                pageSize: 10,
                sortBy: sort,
                orderBy: order
            })

            if (response.error) {
                return
            }

            const { data } = response
            console.log("customer", data.content)
            setCustomers(data.content)
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
                `/api/users/export/csv?pageNo=${currentPage}&pageSize=10&sortBy=${sortBy}&orderBy=${orderBy}`, {
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
            await axios.delete(
                `${server_url}/user-service/api/customers/${targetUser.id}`,
                {
                    withCredentials: true
                })
            await fetchCustomers(currentPage, orderBy, sortBy)

            showMessage(`user with ${targetUser.email} has been deleted successfully`)

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

            const result = await axios.put(`/${server_url}/api/users`, formData, {
                headers: {
                    "Content-Type": "multipart/form-data",
                }
            })
            console.log(result)
            showMessage(`user with ${user.email} has been upated successfully`)
            await fetchCustomers(currentPage, orderBy, sortBy)
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
        //     // let updatedUsers = users.filter(u => u.id !== user.id)
        //     // updatedUsers.push({ ...user, enabled: !user.enabled })

        //     // setCustomers(updatedUsers)
    }

    const navigatePage = (page) => {
        fetchCustomers(page, orderBy, sortBy)
    }

    const showMessage = (msg) => {
        setMessage(msg)

        setTimeout(() => {
            setMessage("");
        }, 3000);

    }
    return (
        <>
            <h1 className='mb-5'>Customers Lists</h1>
            <Row className='d-flex mb-2' md={3}>
                <Form.Group as={Col}>
                    <Form.Control
                        type="search"
                        aria-describedby="search input field"
                        placeholder='Enter keyword to search'
                        onKeyDown={e => {
                            if (e.key === 'Enter') {
                                fetchCustomers(0, 'asc', 'email')
                            }
                        }}
                        onChange={e => {
                            setKeyword(e.target.value)
                        }}
                        value={keyword}
                    />
                </Form.Group>
                <Col className='d-flex me-auto' md={1}>
                    <Button onClick={() => fetchCustomers(0, 'asc', 'email')}>Search</Button>
                    <Button className='ms-2' variant='secondary'
                        onClick={() => {
                            setKeyword('')
                            setCleared(cleared + 1)
                        }
                        }>Clear</Button>
                </Col>
                {/* <Col className='d-flex me-auto' md={2}>
                    <Button className='ms-auto' onClick={
                        () => exportToCSV()
                    }>CSV</Button>
                </Col> */}
                <Col className='ms-auto d-flex'>
                    <Link className='ms-auto mb-2' to="/users/new">
                        <Button variant='secondary'>Create User</Button>
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
                customers?.length === 0 ? (
                    <Row className='text-center mt-5'>
                        <h4>No User Found</h4>
                    </Row>
                ) : (
                    <Row>
                        <Table striped bordered hover>
                            <thead>
                                <tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>
                                        <a type="button"
                                            onClick={() => fetchCustomers(currentPage, orderBy, 'email')}
                                        >Email</a>
                                        {
                                            sortBy === 'email' && (
                                                orderBy === 'asc' ?
                                                    (<BiSolidDownArrow
                                                        type='button'
                                                        onClick={() => { fetchCustomers(currentPage, 'desc', 'email') }}
                                                    />) :
                                                    (<BiSolidUpArrow
                                                        type='button'
                                                        onClick={() => { fetchCustomers(currentPage, 'asc', 'email') }}
                                                    />)
                                            )
                                        }
                                    </th>
                                    <th>
                                        <a type="button"
                                            onClick={() => fetchCustomers(currentPage, orderBy, 'firstName')}
                                        >PhoneNumber</a>
                                        {
                                            sortBy === 'firstName' && (
                                                orderBy === 'asc' ?
                                                    (<BiSolidDownArrow
                                                        type='button'
                                                        onClick={() => { fetchCustomers(currentPage, 'desc', 'firstName') }}
                                                    />) :
                                                    (<BiSolidUpArrow
                                                        type='button'
                                                        onClick={() => { fetchCustomers(currentPage, 'asc', 'firstName') }}
                                                    />)
                                            )
                                        }
                                    </th>
                                    <th>State/Province</th>
                                    <th>Country</th>
                                    <th>Status</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    customers.map(customers => (
                                        <tr key={customers.id} className='align-middle'>
                                            <td>{customers.firstName}</td>
                                            <td>{customers.lastName}</td>
                                            <td>{customers.email}</td>
                                            <td>{customers.phoneNumber}</td>
                                            <td>{customers.state?.name}</td>
                                            <td>{customers.country?.name}</td>
                                            <td>
                                                <RiCheckboxCircleFill
                                                    size={24}
                                                    color={customers.status === 'ACTIVE' ? "green" : "gray"}
                                                    onClick={() => handleActiveToggle(customers)}
                                                />
                                            </td>
                                            <td>
                                                <LinkContainer to={`/customerss/${customers.id}`}>
                                                    <Button variant="seconary"><FaEdit size={24} /></Button>
                                                </LinkContainer>
                                                <TbMinusVertical />
                                                <Button variant="seconary"
                                                    onClick={() => {
                                                        handleModalShow()
                                                        setTargetCustomer(customers)
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
                            <Pagination.Item key={`page-${index}`}
                                active={index === currentPage}
                                onClick={() => navigatePage(index)}
                            >{index + 1}</Pagination.Item>
                        ))
                }

                {
                    (lastPage > 5 && currentPage > 2 && lastPage - currentPage > 3) &&
                    [...Array(5).keys()]
                        .map(index => (
                            <Pagination.Item key={`page-${index}`}
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
                            <Pagination.Item key={`page-${index}`}
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
                <Modal.Body>Do you want to Delete a user with {targetCustomer.email}</Modal.Body>
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

export default UserListScreen