import React, { useEffect, useState } from 'react'
import FormContainer from '../components/FormContainer'
import { Button, Col, Form, Row } from 'react-bootstrap'
import { Link, useLocation, useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import { useRegisterMutation } from '../slices/usersApiSlice'
import { setCredentials } from '../slices/authSlice'
import Loader from '../components/Loader'
import { toast } from 'react-toastify'


function RegisterScreen() {
    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [email, setEmail] = useState('')
    const [phoneNumber, setPhoneNumber] = useState('')
    const [password, setPassword] = useState('')
    const [confirmPassword, setConfirmPassword] = useState('')

    const dispatch = useDispatch()
    const navigate = useNavigate()

    const [register, { isLoading }] = useRegisterMutation()
    const { userInfo } = useSelector(state => state.auth)

    const { search } = useLocation()
    const searchParams = new URLSearchParams(search)
    const redirect = searchParams.get('redirect') || '/'

    useEffect(() => {
        if (userInfo) {
            navigate(redirect)
        }
    }, [userInfo, redirect, navigate])


    const submitHandler = async (e) => {
        e.preventDefault()

        if (password !== confirmPassword) {
            toast.error('비밀번호가 일치하지 않습니다.')
            return
        } else {

            try {
                console.log(email, password, phoneNumber, firstName, lastName)
                const res = await register({
                    email, firstName, lastName, email, phoneNumber, password
                }).unwrap()
                // dispatch(setCredentials({ ...res, }))
                navigate(redirect)
                console.log(document.cookie)
            } catch (error) {
                console.log(error)
                toast.error(error?.data?.message || error.error)
            }
        }
    }

    return (
        <FormContainer>
            <h1>회원가입</h1>
            <Form onSubmit={submitHandler}>
                <Form.Group as={Row}>
                    <Form.Group as={Col} controlId='firstName' className='my-3'>
                        <Form.Label>이름</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder='이름을 입력하세요'
                            value={firstName}
                            onChange={e => setFirstName(e.target.value)}
                        >
                        </Form.Control>
                    </Form.Group>
                    <Form.Group as={Col} controlId='lastName' className='my-3'>
                        <Form.Label>성</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder='성을 입력하세요'
                            value={lastName}
                            onChange={e => setLastName(e.target.value)}
                        >
                        </Form.Control>
                    </Form.Group>
                </Form.Group>
                <Form.Group as={Row}>
                    <Form.Group as={Col} controlId='email' className='my-3'>
                        <Form.Label>이메일 주소</Form.Label>
                        <Form.Control
                            type="email"
                            placeholder='이메일 주소를 입력하세요'
                            value={email}
                            onChange={e => setEmail(e.target.value)}
                        >
                        </Form.Control>
                    </Form.Group>
                    <Form.Group as={Col} controlId='email' className='my-3'>
                        <Form.Label>연락처</Form.Label>
                        <Form.Control
                            type="number"
                            placeholder='전화번호를 입력하세요(숫자만)'
                            value={phoneNumber}
                            onChange={e => setPhoneNumber(e.target.value)}
                        >
                        </Form.Control>
                    </Form.Group>
                </Form.Group>
                <Form.Group as={Row}>
                    <Form.Group as={Col} controlId="password" className='my-3'>
                        <Form.Label>비밀번호</Form.Label>
                        <Form.Control
                            type="password"
                            placeholder='비밀번호를 입력하세요'
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                        >
                        </Form.Control>
                    </Form.Group>
                    <Form.Group as={Col} controlId="confirmPassword" className='my-3'>
                        <Form.Label>비밀번호 확인</Form.Label>
                        <Form.Control
                            type="password"
                            placeholder='비밀번호를 다시 입력해주세요'
                            value={confirmPassword}
                            onChange={e => setConfirmPassword(e.target.value)}
                        >
                        </Form.Control>
                    </Form.Group>
                </Form.Group>
                <Button type="submit" variant='primary' className='my-3' disabled={isLoading}>
                    회원 등록
                </Button>
                {
                    isLoading && <Loader />
                }
            </Form>
            <Row className='py-3'>
                <Col>
                    이미 계정이 있나요?{' '}
                    <Link to={redirect ? `/login?redirect=${redirect}` : '/login'}>
                        로그인
                    </Link>
                </Col>
            </Row >
        </FormContainer >
    )
}

export default RegisterScreen