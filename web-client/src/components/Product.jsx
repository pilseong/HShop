import React from 'react'
import { Card } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import Rating from './Rating'

function Product({ product }) {
    const server_url = import.meta.env.VITE_BASE_URL
    return (
        <Card className='my-3 p-3 rounded'>
            <Link to={`/products/${product.id}`}>
                <Card.Img
                    src={`${server_url}/catalog-service/photos/productimages/${product.id}/${product.mainImage}`} variant='top' />
            </Link>
            <Card.Body>
                <Link to={`/products/${product.id}`}>
                    <Card.Title as="div">
                        <strong>{product.name}</strong>
                    </Card.Title>
                </Link>
                {/* <Card.Text as="div">
                    <Rating value={product.rating} text={`${product.numReviews}`} />
                </Card.Text> */}

                {product.discountPercent > 0 ?
                    (
                        <div className='d-flex align-items-baseline'>
                            <Card.Text style={{ color: 'darkred' }} as="h4">{product.discountPrice}</Card.Text>&nbsp;
                            <del><Card.Text as="h5">{product.displayPrice}</Card.Text></del>
                        </div>
                    ) : (
                        <Card.Text as="h5">
                            {product.displayPrice}
                        </Card.Text>
                    )
                }

            </Card.Body>
        </Card>
    )
}

export default Product