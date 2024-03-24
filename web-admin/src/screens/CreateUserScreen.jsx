import React, { useEffect, useRef, useState } from 'react'
import { Alert, Button, Col, Form, FormGroup, Image, Row } from 'react-bootstrap'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import { AiOutlineCloseSquare } from "react-icons/ai";
import { useObjectUrls } from '../util/objectUrl';

function CreateUserScreen() {
    const [validated, setValidated] = useState(false);
    const getObjectUrl = useObjectUrls()

    const ref = useRef()

    const navigate = useNavigate()
    const [roles, setRoles] = useState([])
    const [error, setError] = useState({
        code: "",
        message: ""
    })
    const [user, setUser] = useState({
        lastName: '',
        firstName: '',
        email: '',
        password: '',
        status: 'INACTIVE',
        roles: []
    })

    const [files, setFiles] = useState([])
    const server_url = import.meta.env.VITE_BASE_URL

    useEffect(() => {
        const fetchRoles = async () => {
            const { data } = await axios.get(`${server_url}/api/roles`)
            setRoles(data.roles)
        }

        fetchRoles()
        setValidated(true);
    }, [])

    const submitCreation = async (event) => {
        const form = event.currentTarget;
        event.preventDefault();
        event.stopPropagation();

        if (form.checkValidity() === false) {
            return
        }

        const formData = new FormData();
        if (files.length > 0) formData.append("image", files[0])
        formData.append("command", new Blob([JSON.stringify(user)], {
            type: "application/json",
        }));

        // POST 요청
        await axios.post(`${server_url}/api/auth/register`, formData, {
            headers: {
                "Content-Type": "multipart/form-data",
            },
        }).then((response) => {
            console.log(response);
            navigate('/')
        }).catch((error) => {
            console.log(error);
            setError(error.response.data)
        });
    }


    return (
        <>
            <h2>Manage Users | Create User</h2>
            {
                error?.code === 'Bad Request' && (
                    <Alert variant='danger' className='mt-2'>
                        <Form.Text className="text-muted">
                            {
                                error.message.split(',').map(err => <><strong>{err}</strong><br /></>)
                            }
                        </Form.Text>
                    </Alert>
                )
            }
            <Form noValidate validated={validated} onSubmit={submitCreation}>
                <Form.Group className="mb-3">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email"
                        value={user.email}
                        onChange={e => {
                            setUser({ ...user, email: e.target.value })
                        }}
                        minLength={4}
                        maxLength={128}
                        required
                    />
                    <Form.Control.Feedback type="invalid">
                        Please choose a email. min is 2 characters
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>First Name</Form.Label>
                    <Form.Control type="text" placeholder="Enter first name"
                        value={user.firstName}
                        onChange={e => {
                            setUser({ ...user, firstName: e.target.value })
                        }}
                        minLength={2}
                        maxLength={32}
                        required
                    />
                    <Form.Control.Feedback type="invalid">
                        should not be empty, and min is 2 and max is 32
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Last Name</Form.Label>
                    <Form.Control type="text" placeholder="Enter last name"
                        value={user.lastName}
                        onChange={e => {
                            setUser({ ...user, lastName: e.target.value })
                        }}
                        minLength={2}
                        maxLength={32}
                        required
                    />
                    <Form.Control.Feedback type="invalid">
                        should not be empty, and min is 4 and max is 32
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password"
                        value={user.password}
                        onChange={e => {
                            setUser({ ...user, password: e.target.value })
                        }}
                        minLength={4}
                        maxLength={32}
                        required
                    />
                    <Form.Control.Feedback type="invalid">
                        should not be empty, and min is 4 and max is 32
                    </Form.Control.Feedback>
                </Form.Group>
                <Row>
                    <Form.Group as={Col} md='2'>
                        <Form.Label>Roles</Form.Label>
                    </Form.Group>
                    <Form.Group as={Col} md="10" className="mb-3">
                        {
                            roles?.length > 0 && roles.map(role => (
                                <Form.Check key={role.name} type="checkbox"
                                    label={`${role.name} - ${role.description}`}
                                    onClick={e => {
                                        let updatedRole = user.roles
                                        if (e.target.checked) {
                                            // 추가
                                            updatedRole = [...updatedRole, role.id]
                                        } else {
                                            // 해제
                                            updatedRole = updatedRole
                                                .filter(r => r !== role.id)
                                        }
                                        setUser({ ...user, roles: updatedRole })
                                    }}
                                />
                            ))
                        }
                    </Form.Group>
                </Row>
                <Row>
                    <Form.Group as={Col} md='2'>
                        <Form.Label>Status</Form.Label>
                    </Form.Group>
                    <Form.Group as={Col} md='10' className="mb-3">
                        <Form.Check type="checkbox" label="Account Enabled" onClick={e => {
                            if (e.target.checked) setUser({ ...user, status: 'ACTIVE' })
                            else setUser({ ...user, status: 'INACTIVE' })
                        }} />
                    </Form.Group>
                </Row>
                <Row className='mb-5 align-items-center'>
                    <Form.Group as={Col} md='2'>
                        <Form.Label>Photo</Form.Label>
                    </Form.Group>
                    <Form.Group as={Col} md='6' className='d-flex'>
                        <Form.Control type="file" accept='image/png, image/jpeg' ref={ref}
                            onChange={event => {
                                const files = Array.from(event.target.files || [])
                                setFiles(files)
                                // 파일이 있는 경우 유저 객체 변경
                                if (files.length > 0)
                                    setUser({ ...user, photo: files[0].name })
                                else
                                    setUser({ ...user, photo: '' })
                            }}
                        />
                        <Button variant='link' onClick={() => {
                            ref.current.value = ''
                            setUser({ ...user, photo: '' })
                            setFiles([])
                        }}>
                            <AiOutlineCloseSquare size={24} />
                        </Button>
                    </Form.Group>
                    <Form.Group as={Col} md="4">
                        {
                            files.map(file => (<Image fluid src={getObjectUrl(file)} thumbnail />))
                        }
                    </Form.Group>
                </Row>
                <FormGroup className='d-flex justify-content-center'>
                    <Button className='me-4' variant="primary" type="submit">
                        Submit
                    </Button>
                    <Button variant="seconary" type="button" onClick={() => navigate('/')}>
                        Cancel
                    </Button>
                </FormGroup>
            </Form>
        </>
    )
}

export default CreateUserScreen