import React from 'react'
import { Alert, Badge, Button, Col, Form, Image, InputGroup, ListGroup, Modal, Pagination, Row, Tab, Table, Tabs } from 'react-bootstrap'
import { useEffect, useState } from 'react'
import axios from 'axios'
import {
    useGetBrandsMutation
} from '../slices/brandsApiSlice';
import FormContainer from '../components/FormContainer';
import Loader from '../components/Loader';
import Message from '../util/Message';
import { useCreateProductMutation } from '../slices/productsApiSlice';


function CreateProductScreen() {

    const [product, setProduct] = useState({
        name: '',
        alias: '',
        status: 'INACTIVE',
        discountPercent: 0,
        cost: 0,
        price: 0,
        stock: 0
    })
    const [brands, setBrands] = useState([])
    const [selectedBrand, setSelectedBrand] = useState('')

    const [generalMessage, setGeneralMessage] = useState("")
    const [countryMessage, setCountryMessage] = useState("")
    const [files, setFiles] = useState([])
    const [country, setCountry] = useState(null)
    const [countryEditMode, setCountryEditMode] = useState('none')
    const [countryForState, setCountryForState] = useState(null)
    const [stateEditMode, setStateEditMode] = useState('none')
    const [state, setState] = useState(null)

    // 세팅 리스트
    const [settings, setSettings] = useState({})
    const [countries, setCountries] = useState([])
    const [error, setError] = useState({
        code: "",
        message: ""
    })


    const [getBrands, { isLoading: isBrandsLoading }] = useGetBrandsMutation()
    const [createProduct, { isLoding: isProductLoading }] = useCreateProductMutation()
    console.log(product)

    // general 데이터 로딩
    useEffect(() => {
        fetchBrands()
    }, [])

    const fetchBrands = async () => {
        try {
            const { data } = await getBrands({
                pageSize: 200,
                sortBy: 'name',
                orderBy: 'asc'
            })
            console.log(data.content)

            setBrands(data.content)

        } catch (error) {
            console.log(error)
        }
    }

    const server_url = import.meta.env.VITE_BASE_URL


    const handleSubmit = async (e) => {
        e.preventDefault()

        // const submitList = []
        // // 바뀐 것이 있는지 확인하고 있으면 배열을 다시 만든다.

        // console.log(submitList)
        const response = await createProduct(product)
        console.log(response)


    }

    return (
        <>
            <h1 className='mb-5'>Manage Products | Create New Product</h1>
            <Row className='d-flex mb-2' lg={3}>
                {
                    generalMessage.length > 0 && (
                        <Alert variant="info">{generalMessage}</Alert>
                    )
                }
            </Row >
            <Tabs
                defaultActiveKey="overview"
                id="uncontrolled-tab-example"
                className="mb-3"
            >
                <Tab eventKey="overview" title="Overview">
                    <FormContainer>
                        <Form onSubmit={handleSubmit}>
                            <Form.Group as={Row} className="mb-3" controlId="product_name">
                                <Form.Label column sm="3" lg={2}>
                                    Product Name
                                </Form.Label>
                                <Col sm="9">
                                    <Form.Control
                                        type="text"
                                        value={product.name}
                                        onChange={e => {
                                            setProduct({
                                                ...product,
                                                'name': e.target.value
                                            })
                                        }}
                                        minLength={3}
                                        required
                                    />
                                </Col>
                            </Form.Group>
                            <Form.Group as={Row} className="mb-3" controlId="alias">
                                <Form.Label column sm="3" lg={2}>
                                    Alias
                                </Form.Label>
                                <Col sm="9">
                                    <Form.Control
                                        type="text"
                                        value={product.alias}
                                        onChange={e => {
                                            setProduct({
                                                ...product,
                                                'alias': e.target.value
                                            })
                                        }}
                                        minLength={3}
                                        required
                                    />
                                </Col>
                            </Form.Group>
                            <Form.Group as={Row} className="mb-3" controlId="brand">
                                <Form.Label column sm="3" lg={2}>
                                    Brand
                                </Form.Label>
                                <Col sm="9">
                                    <Form.Select aria-label="brand" required
                                        onChange={e => {
                                            delete product['categoryId']
                                            if (e.target.value === '') {
                                                delete product['brandId']
                                                setProduct({ ...product })
                                                setSelectedBrand('')
                                            } else {
                                                setProduct({
                                                    ...product,
                                                    brandId: brands[e.target.value].id
                                                })
                                                setSelectedBrand(e.target.value)
                                            }
                                        }}>
                                        <option value="">Choose Brand</option>
                                        {brands &&
                                            brands.map((brand, index) => (
                                                <option
                                                    value={index}
                                                    selected={
                                                        brand.id === product.brandId
                                                    }>
                                                    {brand.name}
                                                </option>
                                            ))
                                        }
                                    </Form.Select>
                                </Col>
                            </Form.Group>
                            <Form.Group as={Row} className="mb-3" controlId="category">
                                <Form.Label column sm="3" lg={2}>
                                    Category
                                </Form.Label>
                                <Col sm="9">
                                    <Form.Select aria-label="category" required
                                        onChange={e => {
                                            if (e.target.value === '') {
                                                delete product['categoryId']
                                                setProduct({ ...product })
                                            } else {
                                                setProduct({
                                                    ...product,
                                                    categoryId: e.target.value
                                                })
                                            }
                                        }}>
                                        <option value="">Choose Category</option>
                                        {
                                            selectedBrand !== '' && brands[selectedBrand]?.categories?.map(category => (
                                                <option
                                                    value={category.id}
                                                    selected={
                                                        category.id === product.categoryId
                                                    }
                                                >
                                                    {category.name}
                                                </option>
                                            ))
                                        }
                                    </Form.Select>
                                </Col>
                            </Form.Group>
                            <Form.Group as={Row} className="mb-3" controlId="status">
                                <Form.Label column sm="3" lg={2}>
                                    Status
                                </Form.Label>
                                <Col sm="9">
                                    <Form.Select aria-label="status"
                                        onChange={e => {
                                            setProduct({
                                                ...product,
                                                status: e.target.value
                                            })
                                        }}>
                                        <option value="ACTIVE" selected={product?.status === 'ACTIVE'}>
                                            ACTIVE
                                        </option>
                                        <option value="INACTIVE" selected={product?.status === 'INACTIVE'}>
                                            INACTIVE
                                        </option>
                                        <option value="OUT_OF_STOCK" selected={product?.status === 'OUT_OF_STOCK'}>
                                            OUT OF STOCK
                                        </option>
                                    </Form.Select>
                                </Col>
                            </Form.Group>

                            <Form.Group as={Row} className="mb-3" controlId="cost">
                                <Form.Label column sm="3" lg={2}>
                                    Cost
                                </Form.Label>
                                <Col sm="9">
                                    <Form.Control
                                        type="number"
                                        value={product.cost}
                                        onChange={e => {
                                            setProduct({
                                                ...product,
                                                'cost': e.target.value
                                            })
                                        }}
                                        required
                                    />
                                </Col>
                            </Form.Group>

                            <Form.Group as={Row} className="mb-3" controlId="price">
                                <Form.Label column sm="3" lg={2}>
                                    Price
                                </Form.Label>
                                <Col sm="9">
                                    <Form.Control
                                        type="number"
                                        value={product.price}
                                        onChange={e => {
                                            setProduct({
                                                ...product,
                                                'price': e.target.value
                                            })
                                        }}
                                        required
                                    />
                                </Col>
                            </Form.Group>

                            <Form.Group as={Row} className="mb-3" controlId="discountPercent">
                                <Form.Label column sm="3" lg={2}>
                                    Discount
                                </Form.Label>
                                <Col sm="9">
                                    <Form.Control
                                        type="number"
                                        value={product.discountPercent}
                                        onChange={e => {
                                            setProduct({
                                                ...product,
                                                'discountPercent': e.target.value
                                            })
                                        }}
                                        required
                                    />
                                </Col>
                            </Form.Group>
                            <Form.Group as={Row} className="mb-3" controlId="stock">
                                <Form.Label column sm="3" lg={2}>
                                    Stock
                                </Form.Label>
                                <Col sm="9">
                                    <Form.Control
                                        type="number"
                                        value={product.stock}
                                        onChange={e => {
                                            setProduct({
                                                ...product,
                                                'stock': e.target.value
                                            })
                                        }}
                                        required
                                    />
                                </Col>
                            </Form.Group>
                            <Form.Group className='d-flex justify-content-center'>
                                <Button type="submit" variant='primary' className='my-3' disabled={isBrandsLoading}>
                                    Save
                                </Button>
                            </Form.Group>
                        </Form>
                    </FormContainer>
                </Tab>
                <Tab eventKey="description" title="Description">
                    <h6>Use this page to manage countries which will be displayed in the customer registration form.</h6>

                    <Button className='my-2'
                        onClick={async () => {
                            if (await fetchCountries() === true) {
                                showMessage("Countries loaded successfully", 'country')
                            } else {
                                showMessage("Countries loaded failed", 'country')
                            }
                        }}
                    >Load Countries</Button>

                    <select className="form-select" size="10">
                        {
                            countries?.map((c, index) => (
                                <option selected={country && c.id === country.id}
                                    onClick={() => {
                                        setCountry(c)
                                        setCountryEditMode('selected')
                                    }}
                                    value={index}>{c.name}</option>
                            ))
                        }

                    </select>
                    <Form onSubmit={handleSubmit} className='my-3'>
                        <InputGroup as={Row} className="my-3 row align-items-center">
                            <InputGroup.Text as={Col} lg={5} md={6} className="mb-3">
                                <Form.Label lg={4} className='me-2 my-auto'>
                                    Country Name
                                </Form.Label>
                                <Form.Control lg={8}
                                    type="text"
                                    value={country === null ? "" : country.name}
                                    disabled={countryEditMode === 'none'}
                                    onChange={e => {
                                        if (country) {
                                            setCountry({ ...country, name: e.target.value })
                                        } else {
                                            setCountry({ name: e.target.value })
                                        }
                                    }}
                                />
                            </InputGroup.Text>
                            <InputGroup.Text as={Col} lg={3} md={6} className="mb-3">
                                <Form.Label lg={4} className='me-2 my-auto'>
                                    Code
                                </Form.Label>
                                <Form.Control
                                    type="text"
                                    max={5}
                                    disabled={countryEditMode === 'none'}
                                    value={country === null ? "" : country.code}
                                    onChange={e => {
                                        if (country)
                                            setCountry({ ...country, code: e.target.value })
                                        else
                                            setCountry({ code: e.target.value })
                                    }}
                                />
                            </InputGroup.Text>
                            <Form.Group as={Col} lg={4} md={6} className="mb-3">
                                <Button className='me-2'
                                    onClick={(e) => {
                                        if (countryEditMode === 'none')
                                            setCountryEditMode('new')
                                        else if (countryEditMode === 'selected') {
                                            setCountryEditMode('none')
                                            setCountry(null)
                                        } else if (countryEditMode === 'new') {
                                            handleCountry('create')
                                        }
                                    }}
                                    disabled={countryEditMode === 'new' &&
                                        (country === null || !country.name || !country.code)}
                                >{countryEditMode === 'none' ? 'New' : countryEditMode ===
                                    'selected' ? 'Reset' : 'Add'}
                                </Button>
                                <Button
                                    disabled={countryEditMode === 'new'}
                                    className='me-2'
                                    onClick={() => {
                                        handleCountry('update')
                                    }}
                                >Update</Button>
                                <Button disabled={countryEditMode === 'new'}
                                    onClick={() => {
                                        handleCountry('delete')
                                    }}
                                >Delete</Button>
                            </Form.Group>
                        </InputGroup>
                    </Form>
                    {
                        countryMessage.length > 0 && (
                            <Alert variant="info">{countryMessage}</Alert>
                        )
                    }
                </Tab>
                <Tab eventKey="images" title="Images">
                    <h6>Use this page to manage states/provinces which will be displayed in the customer registration form.</h6>

                    <Button className='my-2'
                        onClick={async () => {
                            if (await fetchCountries())
                                showMessage("Countries loaded successfully", 'country')
                            else
                                showMessage("Countries loaded failed", 'country')
                        }}
                    >Load Countries</Button>

                    <Form.Group as={Row}>
                        <Form.Label lg={4} className='me-2 my-2'>
                            Country Name
                        </Form.Label>
                        <select className="form-select" size="1"
                            onChange={e => {
                                setCountryForState(countries[e.target.value])
                                console.log(countries[e.target.value])
                                setState(null)
                                setStateEditMode('none')
                            }}
                        >
                            <option selected={countryForState === null}>Select a state</option>
                            {
                                countries?.map((c, index) => (
                                    <option value={index}>{c.name}</option>
                                ))
                            }
                        </select>
                    </Form.Group>
                    <Form.Group as={Row}>
                        <Form.Label lg={4} className='me-2 my-2'>
                            States/Provinces
                        </Form.Label>
                        <select className="form-select" size="10">
                            {
                                countryForState?.states?.map((s, index) => (
                                    <option selected={state && s.id === state.id}
                                        onClick={() => {
                                            setState(s)
                                            setStateEditMode('selected')
                                        }}
                                        value={index}>{s.name}</option>
                                ))
                            }
                        </select>
                    </Form.Group>

                    <Form className='my-3'>
                        <InputGroup as={Row} className="my-3 row align-items-center">
                            <InputGroup.Text as={Col} lg={5} md={6} className="mb-3">
                                <Form.Label lg={4} className='me-2 my-auto'>
                                    State Name
                                </Form.Label>
                                <Form.Control lg={8}
                                    type="text"
                                    value={state === null ? "" : state.name}
                                    disabled={stateEditMode === 'none'}
                                    onChange={e => {
                                        if (state) {
                                            setState({ ...state, name: e.target.value })
                                        } else {
                                            setState({ name: e.target.value })
                                        }
                                    }}
                                />
                            </InputGroup.Text>
                            <Form.Group as={Col} lg={4} md={6} className="mb-3">
                                <Button className='me-2'
                                    onClick={(e) => {
                                        if (stateEditMode === 'none')
                                            // create
                                            setStateEditMode('new')
                                        else if (stateEditMode === 'selected') {
                                            // reset
                                            setStateEditMode('none')
                                            setState(null)
                                        } else if (stateEditMode === 'new') {
                                            // save
                                            handleState('create')
                                        }
                                    }}
                                    disabled={!countryForState}
                                >
                                    {stateEditMode === 'none' ? 'New' : stateEditMode ===
                                        'selected' ? 'Reset' : 'Add'}
                                </Button>
                                <Button
                                    disabled={stateEditMode === 'new' || !countryForState || !state}
                                    className='me-2'
                                    onClick={() => {
                                        handleState('update')
                                    }}
                                >Update</Button>
                                <Button disabled={stateEditMode === 'new' || !countryForState || !state}
                                    onClick={() => {
                                        handleState('delete')
                                    }}
                                >Delete</Button>
                            </Form.Group>
                        </InputGroup>
                    </Form>
                    {
                        countryMessage.length > 0 && (
                            <Alert variant="info">{countryMessage}</Alert>
                        )
                    }
                </Tab>
                <Tab eventKey="details" title="Details">
                    Tab content for Contact
                </Tab>
                <Tab eventKey="shipping" title="Shipping">
                    Tab content for Contact
                </Tab>
            </Tabs >
        </>
    )
}

export default CreateProductScreen