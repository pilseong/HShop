import React from 'react'
import { Col, Container, Row } from 'react-bootstrap'

function Footer() {
    const currentYear = new Date().getFullYear()
    return (
        <footer>
            <Container>
                <Row>
                    <Col className='text-center py-3'>
                        <div>HShop &copy; {currentYear}</div>
                    </Col>
                </Row>
            </Container>
        </footer>
    )
}

export default Footer