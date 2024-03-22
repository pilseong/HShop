import React from 'react'
import { Button, Card, Col, Form, Image, ListGroup, Row } from 'react-bootstrap'
import { useDispatch, useSelector } from 'react-redux'
import { Link, useNavigate } from 'react-router-dom'
import Message from '../components/Message'
import { FaTrash } from 'react-icons/fa'
import { addToCart, removeFromCart } from '../slices/cartSlice'

function CartScreen() {

    const dispatch = useDispatch()
    const navigate = useNavigate()

    const { cartItems } = useSelector(state => state.cart)

    console.log(cartItems)

    const addToCartHandler = async (item, quantity) => {
        dispatch(addToCart({ ...item, quantity }))
    }

    const removeFromCartHandler = async (id) => {
        dispatch(removeFromCart(id))
    }

    const checkoutHandler = () => {
        navigate('/login?redirect=/shipping')
    }

    const server_url = import.meta.env.VITE_BASE_URL

    return (
        <Row>
            <Col md={8}>
                <h1 style={{
                    marginBottom: '20px'
                }}>Shopping Cart</h1>
                {
                    cartItems.length === 0 ? (
                        <Message>
                            Your cart is empty <Link to="/">Go Back</Link>
                        </Message>
                    ) : (
                        <ListGroup variant='flush'>
                            {cartItems.map(item => (
                                <ListGroup.Item key={item._id}>
                                    <Row>
                                        <Col md={2}>
                                            <Image src={`${server_url}/catalog-service/photos/productimages/${item.id}/${item.mainImage}`} alt={item.name} fluid rounded />
                                        </Col>
                                        <Col md={3}>
                                            <Link to={`/products/${item._id}`}>{item.name}</Link>
                                        </Col>
                                        <Col md={2}>${item.price}</Col>
                                        <Col md={2}>
                                            <Form.Control
                                                as="select"
                                                value={item.quantity}
                                                onChange={e => {
                                                    addToCartHandler(item, e.target.value)
                                                }}
                                            >
                                                {
                                                    [...Array(item.countInStock).keys()]
                                                        .map(x => (
                                                            <option key={x + 1} value={x + 1}>
                                                                {x + 1}
                                                            </option>
                                                        ))
                                                }
                                            </Form.Control>
                                        </Col>
                                        <Col md={2}>
                                            <Button type="button" variant="light"
                                                onClick={() => { removeFromCartHandler(item._id) }} >
                                                <FaTrash />
                                            </Button>
                                        </Col>
                                    </Row>
                                </ListGroup.Item>
                            ))}
                        </ListGroup>
                    )
                }
            </Col>
            <Col md={4}>
                <Card>
                    <ListGroup variant='flush'>
                        <ListGroup.Item>
                            <h2>
                                subtotal ({cartItems.reduce((acc, item) => acc + Number(item.quantity), 0)})
                            </h2>
                            ${
                                cartItems
                                    .reduce((acc, item) => acc + item.quantity * item.price, 0)
                                    .toFixed(2)
                            }
                        </ListGroup.Item>
                        <ListGroup.Item>
                            <Button type="button"
                                className='btn-block'
                                disabled={cartItems.lenth === 0}
                                onClick={() => { checkoutHandler() }}
                            >
                                Proceed to Checkout
                            </Button>
                        </ListGroup.Item>
                    </ListGroup>
                </Card>
            </Col>
        </Row >
    )
}

export default CartScreen