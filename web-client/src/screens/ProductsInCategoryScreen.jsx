import React, { useEffect, useState } from 'react'
import { Button, Col, Pagination, Row } from 'react-bootstrap'
import {
    useGetCategoryQuery,
    useGetProductsByCategoryIdMutation
} from '../slices/productsApi'
import Loader from '../components/Loader'
import Message from '../components/Message'
import Product from '../components/Product'
import Category from '../components/Category'
import { useSelector } from 'react-redux'
import { Link, useParams } from 'react-router-dom'
import { skipToken } from '@reduxjs/toolkit/query/react'
import Breadcrumb from '../components/BreadCrumb'

function ProductsInCategoryScreen() {
    const [show, setShow] = useState(false);
    const [targetUser, setTargetUser] = useState({})
    const [message, setMessage] = useState("")
    const [currentPage, setCurrentPage] = useState(0)
    const [lastPage, setLastPage] = useState(0)
    const [orderBy, setOrderBy] = useState('asc')
    const [sortBy, setSortBy] = useState('name')
    const [keyword, setKeyword] = useState("")
    const [cleared, setCleared] = useState(0)
    const [products, setProducts] = useState([])

    const params = useParams()
    // console.log(params)

    const { data: category, isLoading: isCategoryLoading, error: categoryError } =
        useGetCategoryQuery(params.category_alias)

    const [getProducts, isLoading] =
        useGetProductsByCategoryIdMutation()

    // console.log(category)

    useEffect(() => {
        fetchProducts()
    }, [category])

    const fetchProducts = async (page = 0, order = 'asc', sort = 'name') => {
        try {
            const response = await getProducts({
                categoryId: category ? category.content[0].id : skipToken,
                params: {
                    pageNo: page,
                    pageSize: 10,
                    sortBy: sort,
                    orderBy: order
                }
            })


            if (response.error) {
                return
            }

            const { data } = response
            console.log(data)
            setProducts(data.content)
            setCurrentPage(data.pageNo)
            setLastPage(data.totalPages)
            setOrderBy(order)
            setSortBy(sort)

        } catch (error) {
            console.log(error)
        }
    }

    const navigatePage = (page) => {
        console.log("first")
        fetchProducts(page, orderBy, sortBy)
    }


    return (
        <>
            <Breadcrumb root={category?.content[0]} />
            <Row key="first_row" className='align-items-baseline'>
                {
                    category?.content[0]?.subCategories.map(c => (
                        <Col key={c.id} className='col-4' sm={4} md={4} lg={3} xl={1}>
                            <Category category={c}></Category>
                        </Col>
                    ))
                }
            </Row>
            {isCategoryLoading ?
                (<Loader />) :
                categoryError ?
                    (
                        <Message variant='danger'>
                            {categoryError.data?.message || categoryError.error}
                        </Message>
                    ) :
                    (<>
                        <h1>Products in {category?.content[0]?.name}</h1>
                        <Row key="first_row">
                            {
                                products?.map(product => (
                                    <Col key={product.id} sm={12} md={6} lg={4} xl={3}>
                                        <Product product={product}></Product>
                                    </Col>
                                ))
                            }
                        </Row>
                    </>)
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
        </>
    )
}


export default ProductsInCategoryScreen