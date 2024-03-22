import React, { useEffect, useState } from 'react'
import FormContainer from '../components/FormContainer'
import { Button, Col, Form, Row } from 'react-bootstrap'
import { Link, useLocation, useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import { useLoginMutation } from '../slices/usersApiSlice'
import { setCredentials } from '../slices/authSlice'
import Loader from '../components/Loader'
import { toast } from 'react-toastify'


function LoginScreen() {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const dispatch = useDispatch()
    const navigate = useNavigate()

    const [login, { isLoading }] = useLoginMutation()
    const { userInfo } = useSelector(state => state.auth)

    const { search } = useLocation()
    const searchParams = new URLSearchParams(search)
    const redirect = searchParams.get('redirect') || '/'

    useEffect(() => {
        if (userInfo) {
            navigate(redirect)
        }
    }, [userInfo, redirect, navigate])


    const loginSubmitHandler = async (e) => {
        e.preventDefault()
        try {
            const res = await login({ email, password })
            console.log(res)
            if (res.error) {
                // alert()
                // toast.error(error?.data?.message || error.error)
                if (res.error.error)
                    toast.error("시스템 점검중입니다.")
                if (res.error.status === 401)
                    toast.error("입력한 정보가 정확하지 않습니다.")
                return
            }
            console.log(res.data)
            dispatch(setCredentials({ ...res.data, }))
            navigate(redirect)
            // console.log(document.cookie)
        } catch (error) {
            console.log(error)
            toast.error(error?.data?.message || error.error)
        }
    }

    return (
        <FormContainer>
            <h1>로그인</h1>
            <Form onSubmit={loginSubmitHandler}>
                <Form.Group controlId='email' className='my-3'>
                    <Form.Label>이메일</Form.Label>
                    <Form.Control
                        type="email"
                        placeholder='등록된 이메일을 입력하세요'
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                    >
                    </Form.Control>
                </Form.Group>
                <Form.Group controlId="password" className='my-3'>
                    <Form.Label>비밀번호</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder='비밀번호를 입력하세요'
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                    >
                    </Form.Control>
                </Form.Group>
                <Button type="submit" variant='primary' className='my-3' disabled={isLoading}>
                    로그인
                </Button>
                {
                    isLoading && <Loader />
                }
            </Form>
            <Row className='py-3'>
                <Col>
                    처음이신가요? &nbsp;
                    <Link to={redirect ? `/register?redirect=${redirect}` : '/register'}>
                        회원가입
                    </Link>
                </Col>
            </Row >
        </FormContainer >
    )
}

export default LoginScreen