import React from 'react'
import { Card } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import Rating from './Rating'

function Category({ category }) {

    const server_url = import.meta.env.VITE_BASE_URL

    return (
        <div className='d-flex row'>
            <Link to={`/categories/${category.alias}`} className='text-center'>
                <Card.Img
                    src={`${server_url}/catalog-service/photos/categories/${category.id}/${category.image}`}
                    style={{ height: 'auto', maxWidth: "100px" }}
                    variant='top' />
            </Link>
            <Card.Body className='text-center'>
                <Link to={`/categories/${category.alias}`}>
                    <Card.Title as="div">
                        <span style={{ fontSize: 'x-small' }}
                        >{category.name}</span>
                    </Card.Title>
                </Link>
            </Card.Body>
        </div>
    )
}

export default Category