import { Link, useNavigate, useParams } from 'react-router-dom'

import { Button, Card, Col, Image, ListGroup, Row, Form, Modal } from 'react-bootstrap'
import Rating from '../components/Rating'
import { useGetProductQuery } from '../slices/productsApi'
import Loader from '../components/Loader'
import Message from '../components/Message'
import { useEffect, useState } from 'react'
import { useDispatch } from 'react-redux'
import { addToCart } from '../slices/cartSlice'
import { skipToken } from '@reduxjs/toolkit/query/react'
import Breadcrumb from '../components/BreadCrumb'

function ProductScreen() {
    const { product_id } = useParams()

    const [quantity, setQuantity] = useState(1)
    const { data: product, isLoading, error } = useGetProductQuery(
        product_id ? product_id : skipToken,
    )

    const [bigImage, setBigImage] = useState('')


    const dispatch = useDispatch()
    const navigate = useNavigate()

    const addToCartHandler = () => {
        console.log("first")
        dispatch(addToCart({ ...product, quantity }))
        navigate('/login?redirect=/cart')
    }
    const server_url = import.meta.env.VITE_BASE_URL

    console.log(product)

    useEffect(() => {
        if (product) {
            setBigImage(product.mainImage)
        }
    }, [product])

    return (
        <>
            <Breadcrumb root={product?.category} leafName={product?.shortName} />
            {isLoading ?
                (<Loader />) :
                error ?
                    (
                        <Message variant='danger'>
                            {error.data?.message || error.error}
                        </Message>
                    ) :
                    (<>
                        <Link className='btn btn-light my-3' onClick={(() => navigate(-1))}>
                            이전 화면
                        </Link>
                        <Row>
                            <Col md={5} className='d-flex row align-items-center'>
                                <div>
                                    <Image
                                        src={`${server_url}/catalog-service/photos/productimages/${product.id}/${bigImage}`}
                                        alt={product.name} fluid />
                                    <Row className='row justify-content-center'>
                                        <Image thumbnail
                                            className='m-2'
                                            onMouseOver={() => {
                                                setBigImage(`/${product.mainImage}`)
                                            }}
                                            src={`${server_url}/catalog-service/photos/productimages/${product.id}/${product.mainImage}`}
                                            style={{ height: "auto", width: "50px" }}
                                            alt={product.name} fluid />
                                        {
                                            product?.detailImages.map(image => (
                                                <Image thumbnail
                                                    className='m-1'
                                                    onMouseOver={() => {
                                                        setBigImage(`/extras/${image.name}`)
                                                    }}
                                                    src={`${server_url}/catalog-service/photos/productimages/${product.id}/extras/${image.name}`}
                                                    style={{ height: "auto", width: "60px" }}
                                                    alt={product.name} fluid />
                                            ))
                                        }
                                    </Row>
                                </div>
                            </Col>
                            <Col md={4}>
                                <ListGroup variant='flush'>
                                    <ListGroup.Item>
                                        <h3>{product.name}</h3>
                                    </ListGroup.Item>
                                    <ListGroup.Item>
                                        <Rating value={product.rating} text={`${product.numReviews} reviews`} />
                                    </ListGroup.Item>
                                    <ListGroup.Item>Brand: {product.brand.name}</ListGroup.Item>
                                    <ListGroup.Item>
                                        {
                                            product.discountPercent > 0 ? (
                                                <>
                                                    <span>List Price: <del><span className="h6"> {product.displayPrice}</span></del></span><br />
                                                    <span>
                                                        Price: <span style={{ color: 'darkred' }} className="h5"> {product.discountPrice}</span>
                                                        <span>&nbsp;({product.discountPercent}% off)</span>
                                                    </span>
                                                </>
                                            ) : (
                                                <span>Price: <span className="h4"> {product.discountPrice}</span></span>
                                            )
                                        }
                                    </ListGroup.Item>
                                    <ListGroup.Item>
                                        <div dangerouslySetInnerHTML={{ __html: product.shortDescription }}>
                                        </div>
                                    </ListGroup.Item>
                                    {/* <ListGroup.Item>Description: {product.shortDescription}</ListGroup.Item> */}
                                </ListGroup>
                            </Col >
                            <Col md={3}>
                                <Card>
                                    <ListGroup variant='flush'>
                                        <ListGroup.Item>
                                            <Row>
                                                <Col>Price:</Col>
                                                <Col>
                                                    <strong>${product.price}</strong>
                                                </Col>
                                            </Row>
                                        </ListGroup.Item>
                                        {
                                            // 구매가능할 경우만 나오도록
                                            (product?.status === 'ACTIVE' && product.inventory.amount > 0) && (
                                                <ListGroup.Item>
                                                    <Row>
                                                        <Col>Qty</Col>
                                                        <Col>
                                                            <Form.Control
                                                                as="select"
                                                                value={quantity}
                                                                onChange={e => setQuantity(Number(e.target.value))}
                                                            >
                                                                {
                                                                    [...Array(product.inventory.amount).keys()]
                                                                        .map(x => (
                                                                            <option key={x + 1} value={x + 1}>{x + 1}</option>
                                                                        ))
                                                                }
                                                            </Form.Control>
                                                        </Col>
                                                    </Row>
                                                </ListGroup.Item>
                                            )
                                        }
                                        <ListGroup.Item>
                                            <Row>
                                                <Col>Status:</Col>
                                                <Col>
                                                    <strong>
                                                        {(product.status === 'ACTIVE' && product.inventory.amount > 0) ? 'In Stock' :
                                                            (product.inventory === 0 || product.status) ? 'Out of Stock' : 'INACTIVE'}
                                                    </strong>
                                                </Col>
                                            </Row>
                                        </ListGroup.Item>
                                        <ListGroup.Item>
                                            <Button className='btn-block'
                                                type="button"
                                                disabled={product.inventory.amount === 0}
                                                onClick={addToCartHandler}
                                            >
                                                Add To Cart
                                            </Button>
                                        </ListGroup.Item>
                                    </ListGroup>
                                </Card>
                            </Col>
                        </Row >
                        <Row>
                            <Col md={12}>
                                <hr />
                                <h3>Product Description</h3>
                                <div dangerouslySetInnerHTML={{ __html: product.fullDescription }}></div>
                            </Col>
                            <Col md={12}>
                                <hr />
                                <h3>Product Details</h3>
                                {
                                    product?.details.map(detail => (
                                        <div>
                                            <b>{detail.name}</b>: <span>{detail.value}</span>
                                        </div>
                                    ))
                                }

                            </Col>
                        </Row>
                    </>)
            }

            {/* <Modal show="true" >
                <Modal.Header closeButton>
                    <Modal.Title>Delete</Modal.Title>
                </Modal.Header>
                <Modal.Body>Do you want to Delete a user with </Modal.Body>
                <Modal.Footer>

                </Modal.Footer>
            </Modal> */}

        </>
    )
}

export default ProductScreen