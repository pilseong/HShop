import React from 'react'
import { Alert, Form } from 'react-bootstrap'
import { useEffect, useState } from 'react'
import axios from 'axios'
import Message from '../util/Message'
import { Link } from 'react-router-dom';
import { useGetUsersMutation } from '../slices/usersApiSlice';
import EnhancedTable from '../components/EnhancedTable';
import Loader from '../components/Loader';
import AdminModal from '../util/AdminModal'

function UserListScreen() {

    const [showModal, setShowModal] = useState(false);
    const [targetUser, setTargetUser] = useState({})
    const [message, setMessage] = useState("")
    const [currentPage, setCurrentPage] = useState(0)
    const [total, setTotal] = useState(0)
    const [pageSize, setPageSize] = useState(0)
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
            // console.log(data)
            setUsers(data.users)
            setCurrentPage(data.pageNo)
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
        setShowModal(false)
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
        setShowModal(false);
    }

    const handleModalShow = () => setShowModal(true);


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
        <div className='text-gray-600'>
            {
                isLoading ? (<Loader />) : (
                    <div>
                        <h3 className='my-6 text-4xl'>관리자 목록</h3>

                        <div className="flex gap-2">
                            <input type="text"
                                onKeyDown={e => {
                                    if (e.key === 'Enter') {
                                        fetchUsers(0, 'asc', 'email')
                                    }
                                }}
                                onChange={e => {
                                    setKeyword(e.target.value)
                                }}
                                value={keyword}
                                placeholder="Enter keyword" className="bg-gray-100 p-2" />
                            <button className='btn'
                                onClick={() => fetchUsers(0, 'asc', 'email')}
                            >Search</button>
                            <button className='btn'
                                onClick={() => {
                                    setKeyword('')
                                    setCleared(cleared + 1)
                                }}
                            >Clear</button>
                            <Link className='ml-auto' to="/users/new">
                                <button className='btn'>Create User</button>
                            </Link>
                        </div>

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
                                <div className='text-center mt-5'>
                                    <h4>검색 결과가 없습니다.</h4>
                                </div>
                            ) : (
                                <div className='mt-10'>
                                    <EnhancedTable
                                        handleModalShow={handleModalShow}
                                        data={users}
                                        navigatePage={navigatePage}
                                        total={total}
                                        page={currentPage}
                                        size={pageSize || 10}
                                        setTarget={setTargetUser}
                                        headCells={[
                                            {
                                                id: 'id',
                                                type: 'string',
                                                label: 'UserId',
                                            },
                                            {
                                                id: 'photo',
                                                type: 'image',
                                                label: 'Photo',
                                                path: 'http://localhost:8080/user-service/photos/'
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
                                                path: '/users/'
                                            }
                                        ]}
                                    />
                                </div>
                            )
                        }
                        {
                            showModal &&
                            <AdminModal
                                handleModalClose={handleModalClose}
                                deleteHandler={deleteUserHandler}
                                title="관리자 삭제"
                                content={`관리자 ${targetUser.firstName} 을 삭제하시겠습니까?`}
                            />
                        }
                    </div>
                )
            }
        </div>
    )
}

export default UserListScreen