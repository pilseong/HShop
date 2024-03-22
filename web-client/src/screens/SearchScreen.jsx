import React from 'react'
import { Button, Col, Row } from 'react-bootstrap'
import {
    useGetProductsQuery,
    useGetCategoriesQuery
} from '../slices/productsApi'
import Loader from '../components/Loader'
import Message from '../components/Message'
import Product from '../components/Product'
import Category from '../components/Category'
import { useParams } from 'react-router-dom'
import { skipToken } from '@reduxjs/toolkit/query/react'


function SearchScreen() {
    const params = useParams()

    const {
        data: products,
        isLoading: isProductsLoading,
        error: producstError
    } = useGetProductsQuery(params.keyword ? {
        query: params.keyword,
        fullTextSearch: true,
        sortBy: 'name',
        pageSize: 100
    } : skipToken)


    return (
        <>
            {isProductsLoading ?
                (<Loader />) :
                producstError ?
                    (
                        <Message variant='danger'>
                            {isProductsLoading.data?.message || isProductsLoading.error}
                        </Message>
                    ) :
                    (<>
                        {/* <h1>카테고리</h1>
                        <Row key="first_row" className='align-items-center mb-5'>
                            {
                                categories?.content.map(category => (
                                    <Col key={category.id} className={'col-4'} sm={4} md={4} lg={3} xl={1}>
                                        <Category category={category}></Category>
                                    </Col>
                                ))
                            }
                        </Row> */}
                        <h1>'{params.keyword}' 의 검색 결과</h1>
                        <Row key="second_row">
                            {
                                products?.content.length > 0 ? (
                                    products?.content.map(product => (
                                        <Col key={product.id} sm={12} md={6} lg={4} xl={3}>
                                            <Product product={product}></Product>
                                        </Col>
                                    ))
                                ) : (
                                    <h2>검색 결과가 존재하지 않습니다.</h2>
                                )

                            }
                        </Row>
                    </>)
            }
        </>
    )
}


export default SearchScreen