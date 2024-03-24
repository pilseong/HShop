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
import EnhancedTable from '../components/EnhancedTable';
import AdminModal from '../util/AdminModal';


function CategoryListScreen() {

    const [showModal, setShowModal] = useState(false);
    const [targetCategory, setTargetCategory] = useState({})
    const [message, setMessage] = useState("")
    const [currentPage, setCurrentPage] = useState(0)
    const [lastPage, setLastPage] = useState(0)
    const [total, setTotal] = useState(0)
    const [pageSize, setPageSize] = useState(0)
    const [orderBy, setOrderBy] = useState('asc')
    const [sortBy, setSortBy] = useState('name')
    const [keyword, setKeyword] = useState("")
    const [cleared, setCleared] = useState(0)


    // 카테고리 리스트
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

    const fetchCategories = async (page = 0, order = 'asc', sort = 'name', size = 10) => {
        try {
            const { data } = await axios.get(
                `http://localhost:8080/catalog-service/api/categories`, {
                params: {
                    keyword: keyword,
                    pageNo: page,
                    pageSize: size,
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
        setShowModal(false)
    }

    const deleteUserHandler = async () => {
        try {
            await axios.delete(`/api/categories/${targetCategory.id}`)
            await fetchCategories(currentPage, orderBy, sortBy)

            showMessage(`user with ${targetCategory.name} has been deleted successfully`)

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

    const navigatePage = (page, size = pageSize) => {
        fetchCategories(page, orderBy, sortBy, size)
    }

    const showMessage = (msg) => {
        setMessage(msg)

        setTimeout(() => {
            setMessage("");
        }, 3000);

    }
    return (
        <div>
            <h3 className='my-6 text-4xl'>카테고리 목록</h3>

            <div className="flex gap-2">
                <input type="text"
                    onKeyDown={e => {
                        if (e.key === 'Enter') {
                            fetchCategories(0, 'asc', 'name')
                        }
                    }}
                    onChange={e => {
                        setKeyword(e.target.value)
                    }}
                    value={keyword}
                    placeholder="Enter keyword" className="bg-gray-100 p-2" />
                <button className='btn'
                    onClick={() => fetchCategories(0, 'asc', 'name')}
                >Search</button>
                <button className='btn'
                    onClick={() => {
                        setKeyword('')
                        setCleared(cleared + 1)
                    }}
                >Clear</button>
                <Link className='ml-auto' to="/categories/new">
                    <button className='btn'>Create Category</button>
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
                categories.length === 0 ? (
                    <div className='text-center mt-5'>
                        <h4>검색 결과가 없습니다.</h4>
                    </div>
                ) : (

                    <div className='mt-10'>
                        <EnhancedTable
                            handleModalShow={handleModalShow}
                            data={categories}
                            navigatePage={navigatePage}
                            total={total}
                            page={currentPage}
                            size={pageSize || 10}
                            setTarget={setTargetCategory}
                            headCells={[
                                {
                                    id: 'id',
                                    type: 'string',
                                    label: 'Category Id',
                                },
                                {
                                    id: 'image',
                                    type: 'image',
                                    label: 'Photo',
                                    path: 'http://localhost:8080/catalog-service/photos/categories/'
                                },
                                {
                                    id: 'name',
                                    type: 'string',
                                    label: 'Category Name',
                                },
                                {
                                    id: 'alias',
                                    type: 'string',
                                    label: 'Alias',
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
                                    path: '/categories/'
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
                    title="카테고리 삭제"
                    content={`카테고리 ${targetCategory.name} 을 삭제하시겠습니까?`}
                />
            }
        </div>
    )
}

export default CategoryListScreen