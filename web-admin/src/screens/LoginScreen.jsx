import React, { useEffect, useState } from 'react'
import FormContainer from '../components/FormContainer'
import { Button, Col, Form, Row } from 'react-bootstrap'
import { Link, useLocation, useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import { useLoginMutation } from '../slices/usersApiSlice'
import { setCredentials } from '../slices/authSlice'
import Loader from '../components/Loader'
import { jwtDecode } from 'jwt-decode'

function LoginScreen() {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const dispatch = useDispatch()
    const navigate = useNavigate()

    const [login, { isLoading }] = useLoginMutation()
    const { userInfo } = useSelector(state => state.auth)

    const { search } = useLocation()
    const searchParams = new URLSearchParams(search)
    const redirect = searchParams.get('redirect') || '/users'

    useEffect(() => {
        if (userInfo) {
            navigate(redirect)
        }
    }, [userInfo, redirect, navigate])


    const loginSubmitHandler = async (e) => {
        e.preventDefault()
        try {
            console.log(email, password)
            const res = await login({ email, password }).unwrap()
            const userInfo = jwtDecode(res.accessToken)
            dispatch(setCredentials({ ...userInfo }))
            navigate(redirect)
            console.log(document.cookie)
        } catch (error) {
            console.log(error)
            // toast.error(error?.data?.message || error.error)
        }
    }

    return (
        <FormContainer>
            <h1>Sign In</h1>
            <Form onSubmit={loginSubmitHandler}>
                <Form.Group controlId='email' className='my-3'>
                    <Form.Label>Email Address</Form.Label>
                    <Form.Control
                        type="email"
                        placeholder='Enter email'
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                    >
                    </Form.Control>
                </Form.Group>
                <Form.Group controlId="password" className='my-3'>
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder='Enter password'
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                    >
                    </Form.Control>
                </Form.Group>
                <Button type="submit" variant='primary' className='my-3' disabled={isLoading}>
                    Sign In
                </Button>
                {
                    isLoading && <Loader />
                }
            </Form>
            {/* <Row className='py-3'>
                <Col>
                    New Customer ? &nbsp;
                    <Link to={redirect ? `/register?redirect=${redirect}` : '/register'}>
                        Register
                    </Link>
                </Col>
            </Row > */}
        </FormContainer >
    )
}

export default LoginScreen