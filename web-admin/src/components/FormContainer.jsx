
import React from 'react'
import { Col, Container, Row } from 'react-bootstrap'

function FormContainer({ children }) {
    return (
        <Container>
            <Row className='justify-content-md'>
                <Col xs={12} sm={12} md={10} >
                    {children}
                </Col>
            </Row>
        </Container>
    )
}

export default FormContainer