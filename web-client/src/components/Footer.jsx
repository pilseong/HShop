import React from 'react'
import { Col, Container, Row } from 'react-bootstrap'
import { useSelector } from 'react-redux'

function Footer() {

    const { settings } = useSelector(state => state.settings)
    // console.log(settings)

    const currentYear = new Date().getFullYear()
    return (
        <footer>
            <Container>
                <Row>
                    <Col className='text-center py-3'>
                        <div>{settings && settings['COPYRIGHT']?.value}</div>
                    </Col>
                </Row>
            </Container>
        </footer>
    )
}

export default Footer