import React from 'react'
import { Alert, Col, Form, Image, Modal, Pagination, Row, Table } from 'react-bootstrap'
import { useEffect, useState } from 'react'
import axios from 'axios'
import Message from '../util/Message'
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { useGetUsersMutation } from '../slices/usersApiSlice';
import { Button, Container, InputBase, TableContainer, TablePagination, Toolbar, Typography, styled } from '@mui/material';
import { Box, margin } from '@mui/system';
import EnhancedTable from '../components/EnhancedTable';
import Loader from '../components/Loader';

const Search = styled(Box)(() => ({
    backgroundColor: "#eee",
    padding: "0 10px",
    borderRadius: 1,
    width: "100%"
}))

function UserListScreen() {

    const [show, setShow] = useState(false);
    const [targetUser, setTargetUser] = useState({})
    const [message, setMessage] = useState("")
    const [currentPage, setCurrentPage] = useState(0)
    const [total, setTotal] = useState(0)
    const [pageSize, setPageSize] = useState(0)
    const [lastPage, setLastPage] = useState(0)
    const [orderBy, setOrderBy] = useState('asc')
    const [sortBy, setSortBy] = useState('email')
    const [keyword, setKeyword] = useState("")
    const [cleared, setCleared] = useState(0)

    // 유저리스트
    const [users, setUsers] = useState([])
    const [error, setError] = useState({
        code: "",
        message: ""
    })

    // clear 버튼을 눌렀을 때 keyword가 클리어 된 후에 실행되도록 추가
    useEffect(() => {
        if (cleared > 0)
            fetchUsers(0, 'asc', 'email')
    }, [cleared])

    useEffect(() => {
        fetchUsers(0)
    }, [])

    const [getUsers, { isLoading }] = useGetUsersMutation()

    const server_url = import.meta.env.VITE_BASE_URL


    const fetchUsers = async (page = 0, order = 'asc', sort = 'email', size = 10) => {
        try {
            const response = await getUsers({
                keyword: keyword,
                pageNo: page,
                pageSize: size,
                sortBy: sort,
                orderBy: order
            })

            if (response.error) {
                return
            }

            const { data } = response
            console.log(data)
            setUsers(data.users)
            setCurrentPage(data.pageNo)
            setLastPage(data.totalPages)
            setOrderBy(order)
            setPageSize(data.pageSize)
            setTotal(data.totalElements)
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
                `${server_url}/user-service/api/users/${targetUser.id}`,
                {
                    withCredentials: true
                })
            await fetchUsers(currentPage, orderBy, sortBy)

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
            await fetchUsers(currentPage, orderBy, sortBy)
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

        //     // setUsers(updatedUsers)
    }

    const navigatePage = (page, size = pageSize) => {
        setPageSize(size)
        fetchUsers(page, orderBy, sortBy, size)
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
                isLoading ? (<Loader />) : (
                    <Container maxWidth="xl">
                        <Typography variant='h3' my={3}>User Lists</Typography>

                        <Toolbar sx={{
                            display: "flex",
                            justifyContent: "space-between"
                        }}>
                            <Box sx={{ display: "flex", flexGrow: 4 }}>
                                <Search mr={1}>
                                    <InputBase
                                        onKeyDown={e => {
                                            if (e.key === 'Enter') {
                                                fetchUsers(0, 'asc', 'email')
                                            }
                                        }}
                                        onChange={e => {
                                            setKeyword(e.target.value)
                                        }}
                                        value={keyword}
                                        placeholder='Enter keyword' />
                                </Search>
                                <Button size='small'
                                    onClick={() => fetchUsers(0, 'asc', 'email')}
                                    variant='contained' sx={{ marginRight: 1 }}>Search</Button>
                                <Button size='small'
                                    onClick={() => {
                                        setKeyword('')
                                        setCleared(cleared + 1)
                                    }}
                                    variant='contained'>Clear</Button>
                            </Box>
                            <Box sx={{ flexGrow: 6 }} textAlign={'end'}>
                                <Link to="/users/new">
                                    <Button size='small' variant='contained'>Create User</Button>
                                </Link>
                            </Box>

                        </Toolbar >

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
                            users.length === 0 ? (
                                <Row className='text-center mt-5'>
                                    <h4>No User Found</h4>
                                </Row>
                            ) : (
                                <Row>
                                    <EnhancedTable data={users} navigatePage={navigatePage} total={total} page={currentPage} size={pageSize}
                                        headCells={[
                                            {
                                                id: 'userId',
                                                type: 'string',
                                                label: 'UserId',
                                            },
                                            {
                                                id: 'photo',
                                                type: 'image',
                                                label: 'Photo',
                                            },
                                            {
                                                id: 'email',
                                                type: 'string',
                                                label: 'Email',
                                            },
                                            {
                                                id: 'firstName',
                                                type: 'string',
                                                label: 'First Name',
                                            },
                                            {
                                                id: 'lastName',
                                                type: 'string',
                                                label: 'Last Name',
                                            },
                                            {
                                                id: 'roles',
                                                type: 'string',
                                                label: 'Roles',
                                            },
                                            {
                                                id: 'status',
                                                type: 'checkbox',
                                                label: 'Status',
                                            },
                                            {
                                                id: 'management',
                                                type: 'management',
                                                label: '',
                                            }
                                        ]}
                                    />
                                </Row >
                            )
                        }

                        <Modal show={show} onHide={handleModalClose}>
                            <Modal.Header closeButton>
                                <Modal.Title>Delete</Modal.Title>
                            </Modal.Header>
                            <Modal.Body>Do you want to Delete a user with {targetUser.email}</Modal.Body>
                            <Modal.Footer>
                                <Button variant="secondary" onClick={handleModalClose}>
                                    Close
                                </Button>
                                <Button variant="primary" onClick={deleteUserHandler}>
                                    Delete User
                                </Button>
                            </Modal.Footer>
                        </Modal>
                    </Container>
                )
            }

        </>


    )
}

export default UserListScreen