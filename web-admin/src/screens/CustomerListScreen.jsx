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
import EnhancedTable from '../components/EnhancedTable';


function CustomerListScreen() {

    const [showModal, setShowModal] = useState(false);
    const [targetCustomer, setTargetCustomer] = useState({})
    const [message, setMessage] = useState("")
    const [currentPage, setCurrentPage] = useState(0)
    const [lastPage, setLastPage] = useState(0)
    const [orderBy, setOrderBy] = useState('asc')
    const [sortBy, setSortBy] = useState('email')
    const [total, setTotal] = useState(0)
    const [pageSize, setPageSize] = useState(0)
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


    const fetchCustomers = async (page = 0, order = 'asc', sort = 'email', size = 10) => {
        try {
            const response = await getCustomers({
                query: keyword,
                pageNo: page,
                pageSize: size,
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
            setPageSize(data.pageSize)
            setTotal(data.totalElements)
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
        setShowModal(false)
    }

    const deleteCustomerHandler = async () => {
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
        <div className='text-gray-600'>
            <h3 className='my-6 text-4xl'>고객 목록</h3>
            <div className="flex gap-2">
                <input type="text"
                    onKeyDown={e => {
                        if (e.key === 'Enter') {
                            fetchCustomers(0, 'asc', 'email')
                        }
                    }}
                    onChange={e => {
                        setKeyword(e.target.value)
                    }}
                    value={keyword}
                    placeholder="Enter keyword" className="bg-gray-100 p-2" />
                <button className='btn'
                    onClick={() => fetchCustomers(0, 'asc', 'email')}
                >Search</button>
                <button className='btn'
                    onClick={() => {
                        setKeyword('')
                        setCleared(cleared + 1)
                    }}
                >Clear</button>
                <Link className='ml-auto' to="/customers/new">
                    <button className='btn'>Create Customer</button>
                </Link>
            </div>
            {
                error?.code && (
                    <Message variant='danger' className='mt-2'>
                        <div className="text-xs text-gray-300">
                            {
                                error.message
                                    .split(',')
                                    .map(err => <><strong>{err}</strong><br /></>)
                            }
                        </div>
                    </Message>
                )
            }
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
                    <div className='mt-10'>
                        <EnhancedTable
                            handleModalShow={handleModalShow}
                            data={customers}
                            navigatePage={navigatePage}
                            total={total}
                            page={currentPage}
                            size={pageSize || 10}
                            setTarget={setTargetCustomer}
                            headCells={[
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
                                    id: 'email',
                                    type: 'string',
                                    label: 'Email',
                                },
                                {
                                    id: 'phoneNumber',
                                    type: 'string',
                                    label: 'Phone Number',
                                },
                                {
                                    id: 'state',
                                    type: 'string',
                                    label: 'State/Province',
                                },
                                {
                                    id: 'country',
                                    type: 'string',
                                    label: 'Country',
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
                                    path: '/brands/'
                                }
                            ]}
                        />
                    </div>
                )
            }
            {
                showModal && <AdminModal
                    handleModalClose={handleModalClose}
                    deleteHandler={deleteCustomerHandler}
                    title="고객 삭제"
                    content={`${targetCustomer.name} 고객을 삭제하시겠습니까?`}
                />
            }
        </div>
    )
}

export default CustomerListScreen