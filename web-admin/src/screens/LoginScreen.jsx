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
        }
    }

    return (
        <div className='font-body text-gray-600 flex justify-center'>
            <div>
                <h1 className="text-4xl text-center my-20">Log In</h1>
                <form className="text-center" onSubmit={loginSubmitHandler}>
                    <div className='flex my-4 justify-between items-center'>
                        <div className='mr-4'>Email Address</div>
                        <input className="border-2 p-2"
                            type="email"
                            placeholder='Enter email'
                            value={email}
                            onChange={e => setEmail(e.target.value)}
                        ></input>
                    </div>
                    <div className="flex my-4 justify-between items-center">
                        <div className='mr-4'>Password</div>
                        <input className="border-2  p-2"
                            type="password"
                            placeholder='Enter password'
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                        ></input>
                    </div>
                    <button type='submit' className="mt-4 py-2 px-4 uppercase text-xs font-bold cursor-pointer tracking-wider border-2 hover:bg-gray-600 hover:text-white">제출</button>
                </form>
            </div>
        </div>
        // <FormContainer>
        //     <h1>Sign In</h1>
        //     <Form onSubmit={loginSubmitHandler}>
        //         <Form.Group controlId='email' className='my-3'>
        //             <Form.Label>Email Address</Form.Label>
        //             <Form.Control
        //                     type = "email"
        // placeholder = 'Enter email'
        // value = { email }
        // onChange = { e => setEmail(e.target.value) }
        //             >
        //             </Form.Control>
        //         </Form.Group>
        //         <Form.Group controlId="password" className='my-3'>
        //             <Form.Label>Password</Form.Label>
        //             <Form.Control
        //                 type="password"
        //                 placeholder='Enter password'
        //                 value={password}
        //                 onChange={e => setPassword(e.target.value)}
        //             >
        //             </Form.Control>
        //         </Form.Group>
        //         <Button type="submit" variant='primary' className='my-3' disabled={isLoading}>
        //             Sign In
        //         </Button>
        //         {
        //             isLoading && <Loader />
        //         }
        //     </Form>
        //     {/* <Row className='py-3'>
        //         <Col>
        //             New Customer ? &nbsp;
        //             <Link to={redirect ? `/register?redirect=${redirect}` : '/register'}>
        //                 Register
        //             </Link>
        //         </Col>
        //     </Row > */}
        // </FormContainer >
    )
}

export default LoginScreen
