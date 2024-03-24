import React, { useEffect, useRef, useState } from 'react'
import { Alert, Button, Col, Form, FormGroup, Image, Modal, Row } from 'react-bootstrap'
import { AiOutlineCloseSquare } from "react-icons/ai";
import { FaPortrait } from "react-icons/fa";
import axios from 'axios'
import { useNavigate, useParams } from 'react-router-dom'
import { useObjectUrls } from '../util/objectUrl';
import {
    useGetAllRolesQuery,
    useEditUserMutation,
    useGetUserDetailsMutation
} from '../slices/usersApiSlice';

function EditUserScreen() {

    const { userId } = useParams()
    const navigate = useNavigate()

    const ref = useRef()

    const getObjectUrl = useObjectUrls()

    const [validated, setValidated] = useState(false);

    const [error, setError] = useState({
        code: "",
        message: ""
    })
    const [user, setUser] = useState({
        "id": "",
        "email": "",
        "firstName": "",
        "lastName": "",
        "photo": "",
        "status": "INACTIVE",
        "roles": [],
        "password": ""
    })

    const [files, setFiles] = useState([])
    const [photoUpdated, setPhotoUpdated] = useState(false)
    const server_url = import.meta.env.VITE_BASE_URL

    const [getUser, { isLoading: getUserLoading }] = useGetUserDetailsMutation()
    // const [editUser, { isLoading: editUserLoading }] = useEditUserMutation()
    const { data: roles_data } = useGetAllRolesQuery()

    useEffect(() => {
        // 사용자 정보를 가지고 온다.
        const fetchUser = async () => {
            try {
                const { data } = await getUser(userId)
                setUser({ ...data.users[0], roles: data.users[0].roles.map(r => r.id) })
                // console.log({ ...data.users[0], roles: data.users[0].roles.map(r => r.id) })
            } catch (error) {
                console.log(error)
                navigate("/users")
            }
        }
        fetchUser()
    }, [])

    // 수정 실행 메소드
    const submitEdition = async (event) => {
        const form = event.currentTarget;
        event.preventDefault();
        event.stopPropagation();

        setValidated(true);
        if (form.checkValidity() === false) {
            return
        }

        // 데이터 생성
        const formData = new FormData();
        if (files.length > 0) formData.append("image", files[0])
        formData.append("command", new Blob([JSON.stringify(user)], {
            type: "application/json",
        }));

        // POST 요청
        try {
            const response = await axios
                .put(`${server_url}/user-service/api/users/${userId}`, formData, {
                    headers: {
                        "Content-Type": "multipart/form-data",
                    },
                    withCredentials: true,
                })

            console.log(response)
            navigate('/users')
        } catch (error) {
            console.log(error)
            setError(error.response.data)
            return
        }
    }

    return (
        <>
            <h2>Manage Users | Edit User</h2>
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
            <Form noValidate validated={validated} onSubmit={submitEdition}>
                <Form.Group className="mb-3">
                    <Form.Label>First Name</Form.Label>
                    <Form.Control type="text" placeholder="Enter first name" value={user.firstName}
                        onChange={e => {
                            setUser({ ...user, firstName: e.target.value })
                        }}
                        minLength={4}
                        maxLength={32}
                        required
                    />
                    <Form.Control.Feedback type="invalid">
                        should not be empty, and min is 4 and max is 32
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Last Name</Form.Label>
                    <Form.Control type="text" placeholder="Enter last name" value={user.lastName}
                        onChange={e => {
                            setUser({ ...user, lastName: e.target.value })
                        }}
                        minLength={4}
                        maxLength={32}
                        required
                    />
                    <Form.Control.Feedback type="invalid">
                        should not be empty, and min is 4 and max is 32
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" value={user.password}
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
                    <Form.Group as={Col} md='10' className="mb-3">
                        {
                            (roles_data?.roles && roles_data?.roles.length > 0) && roles_data?.roles.map(role => (
                                <Form.Check key={role.name} type="checkbox" label={`${role.name} - ${role.description}`}
                                    onChange={e => {
                                        let updatedRole = user.roles
                                        if (e.target.checked) {
                                            // 추가
                                            updatedRole = [...updatedRole, role.id]
                                        } else {
                                            // 해제
                                            updatedRole = updatedRole.filter(r => r !== role.id)
                                        }
                                        setUser({ ...user, roles: updatedRole })
                                    }}

                                    checked={user.roles.includes(role.id)}
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
                        <Form.Check type="checkbox" label="Account Enabled"
                            checked={user.status === 'ACTIVE'}
                            onClick={e => {
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
                                setPhotoUpdated(true)
                            }}
                        />
                        <Button variant='link' onClick={() => {
                            ref.current.value = ''
                            setUser({ ...user, photo: '' })
                            setFiles([])
                            setPhotoUpdated(true)
                        }}>
                            <AiOutlineCloseSquare size={24} />
                        </Button>
                    </Form.Group>
                    <Form.Group as={Col} md="4">
                        {
                            (!photoUpdated && user.photo) && (
                                < Image width={240} thumbnail fluid
                                    src={`http://localhost:8080/user-service/photos/${user.id}/${user.photo}`} />
                            )
                        }
                        {
                            (!photoUpdated && !user.photo) ? (
                                <FaPortrait className='icon-sliver' size={180} />
                            ) : files.map(file => (<Image width={240} fluid src={getObjectUrl(file)} thumbnail />))
                        }
                    </Form.Group >
                </Row >
                <FormGroup className='d-flex justify-content-center'>
                    <Button className='me-4' variant="primary" type="submit">
                        Submit
                    </Button>
                    <Button variant="seconary" type="button" onClick={() => navigate('/users')}>
                        Cancel
                    </Button>
                </FormGroup>
            </Form >
        </>
    )
}

export default EditUserScreen