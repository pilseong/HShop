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


function HomeScreen() {
    const { data: products, isLoading: isProductLoading, error: productError } = useGetProductsQuery({
        pageSize: 100
    })
    const {
        data: categories,
        isLoading: isCategoryLoading,
        error: categoryError
    } = useGetCategoriesQuery({
        isLeaf: true,
        sortBy: 'name',
        orderBy: 'asc',
        enabled: 'ACTIVE',
        pageSize: 100
    })

    return (
        <>
            {isProductLoading || isCategoryLoading ?
                (<Loader />) :
                productError || categoryError ?
                    (
                        <Message variant='danger'>
                            {isProductLoading.data?.message || isProductLoading.error}
                        </Message>
                    ) :
                    (<>
                        <h1>카테고리</h1>
                        <Row key="first_row" className='align-items-center mb-5'>
                            {
                                categories?.content.map(category => (
                                    <Col key={category.id} className={'col-4'} sm={4} md={4} lg={3} xl={1}>
                                        <Category category={category}></Category>
                                    </Col>
                                ))
                            }
                        </Row>
                        <h1>최신목록</h1>
                        <Row key="second_row">
                            {
                                products?.content.map(product => (
                                    <Col key={product.id} sm={12} md={6} lg={4} xl={3}>
                                        <Product product={product}></Product>
                                    </Col>
                                ))
                            }
                        </Row>
                    </>)
            }
        </>
    )
}


export default HomeScreen