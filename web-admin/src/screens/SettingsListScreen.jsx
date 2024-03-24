import React from 'react'
import { Alert, Badge, Button, Col, Form, Image, InputGroup, ListGroup, Modal, Pagination, Row, Tab, Table, Tabs } from 'react-bootstrap'
import { useEffect, useState } from 'react'
import axios from 'axios'
import {
    useGetCountriesMutation,
    useGetCurrenciesQuery,
    useGetSettingsMutation
} from '../slices/settingsApiSlice';
import FormContainer from '../components/FormContainer';
import Loader from '../components/Loader';
import Message from '../util/Message';


function SettingsListScreen() {

    const [show, setShow] = useState(false);
    const [targetBrand, setTargetBrand] = useState({})
    const [generalMessage, setGeneralMessage] = useState("")
    const [countryMessage, setCountryMessage] = useState("")
    const [original, setOriginal] = useState({})
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


    const [getSettings, { isLoading }] = useGetSettingsMutation()
    const [getCountries, { isLoading: isCountriesLoading }] = useGetCountriesMutation()
    const { data: currencies } = useGetCurrenciesQuery()
    console.log(currencies)


    // general 데이터 로딩
    useEffect(() => {
        fetchSettings()
    }, [])

    const transformToSettingObj = (originalSettings) => {
        return originalSettings.reduce((result, obj) => {
            return result = { ...result, [obj.key]: obj }
        }, {})
    }

    const fetchSettings = async () => {
        try {
            const { data } = await getSettings()

            const settingsObj = transformToSettingObj(data.content)

            setOriginal(JSON.parse(JSON.stringify(settingsObj)))
            setSettings(settingsObj)
            console.log(settingsObj)

        } catch (error) {
            console.log(error)
        }
    }

    const handleModalClose = () => {
        setShow(false)
    }

    const fetchCountries = async () => {
        try {
            // const { data } = await getCountries()
            const response = await getCountries()

            if (response?.error) {
                console.log(esponse?.error)
                return false
            }

            console.log(response)
            setCountries(response?.data?.content)

            if (countryForState) {
                setCountryForState(response?.data.content
                    .filter(country => country.id === countryForState.id)[0])
            }

            return true
        } catch (e) {
            console.log(e)
            return false
        }
    }

    const deleteUserHandler = async () => {
        try {
            await axios.delete(`/api/settings/${targetBrand.id}`)
            await fetchSettings(currentPage, orderBy, sortBy)

            showMessage(`user with ${targetBrand.name} has been deleted successfully`, 'general')

        } catch (error) {
            console.log(error)
            setError(error.response.data)
        }
        setShow(false);
    }

    const handleModalShow = () => setShow(true);

    const server_url = import.meta.env.VITE_BASE_URL


    // 수정 실행 메소드
    const submitEdition = async (submitList) => {
        try {
            // 데이터 생성
            const formData = new FormData();
            if (files.length > 0) formData.append("image", files[0])
            formData.append("command", new Blob([JSON.stringify(submitList)], {
                type: "application/json",
            }));

            const { data } = await axios
                .put(`${server_url}/settings-service/api/settings`, formData, {
                    headers: {
                        "Content-Type": "multipart/form-data",
                    },
                    withCredentials: true,
                })

            console.log(data)
            const settingsObj = transformToSettingObj(data)

            setOriginal(JSON.parse(JSON.stringify(settingsObj)))
            setSettings(settingsObj)
            showMessage(`${submitList.length} has been upated successfully`, 'general')

        } catch (error) {
            console.log(error)
            // setError(error)
            return
        }
    }

    const handleActiveToggle = async (user) => {

        // 서버에 변경, 패스워드 공백은 검증로직 예외
        await submitEdition({
            ...user,
            password: '',
            status: user.status === 'ACTIVE' ? 'INACTIVE' : 'ACTIVE',
            roles: user.roles.map(r => r.id)
        })
    }

    const navigatePage = (page) => {
        fetchSettings(page, orderBy, sortBy)
    }

    const showMessage = (msg, type) => {
        if (type === 'general') {
            setGeneralMessage(msg)

            setTimeout(() => {
                setGeneralMessage("")
            }, 3000);
        } else if (type === 'country') {
            setCountryMessage(msg)

            setTimeout(() => {
                setCountryMessage("");
            }, 3000)
        }
    }

    const handleCountry = async (mode) => {
        try {
            if (mode === 'create' && countryEditMode === 'new') {
                console.log(country)
                await axios.post(`${server_url}/settings-service/api/settings/countries`,
                    country,
                    { withCredentials: true }
                )
                showMessage(`country is saved successfully`, 'country')
            }
            else if (mode === 'update' && countryEditMode === 'selected') {
                console.log(country)
                await axios.put(`${server_url}/settings-service/api/settings/countries/${country.id}`, country,
                    { withCredentials: true }
                )
                showMessage(`country is edited successfully`, 'country')
            }
            else if (mode === 'delete' && countryEditMode == 'selected') {
                console.log(mode)
                await axios.delete(`${server_url}/settings-service/api/settings/countries/${country.id}`,
                    { withCredentials: true }
                )
                showMessage(`country is removed successfully`, 'country')
            }
            setCountry(null)
            setCountryEditMode('none')

            fetchCountries()

            // showMessage(`user with ${targetUser.email} has been deleted successfully`, 'country')

        } catch (error) {
            console.log(error)
            setError(error.response.data)
        }
        setShow(false);
    }


    const handleState = async (mode) => {
        try {
            if (mode === 'create' && stateEditMode === 'new') {
                console.log('create', countryForState)
                await axios.post(`${server_url}/settings-service/api/settings/countries/${countryForState.id}/states`,
                    state,
                    { withCredentials: true }
                )
                showMessage(`state is created successfully`, 'country')
            }
            else if (mode === 'update' && stateEditMode === 'selected') {
                console.log('update', countryForState)
                await axios.put(
                    `${server_url}/settings-service/api/settings/countries/${countryForState.id}/states/${state.id}`,
                    state,
                    { withCredentials: true }
                )
                showMessage(`state is edited successfully`, 'country')
            }
            else if (mode === 'delete' && stateEditMode == 'selected') {
                console.log('delete', countryForState)
                await axios.delete(
                    `${server_url}/settings-service/api/settings/countries/${countryForState.id}/states/${state.id}`,
                    { withCredentials: true }
                )
                showMessage(`state is removed successfully`, 'country')
            }
            setState(null)
            setStateEditMode('none')

            fetchCountries()

            // showMessage(`user with ${targetUser.email} has been deleted successfully`, 'country')

        } catch (error) {
            console.log(error)
            setError(error.response.data)
        }
        setShow(false);
    }


    const handleSubmit = (e) => {
        e.preventDefault()
        // console.log(original)

        const submitList = []
        // 바뀐 것이 있는지 확인하고 있으면 배열을 다시 만든다.
        for (const name in original) {
            if (original[name].value != settings[name].value) {
                submitList.push(settings[name])
            }
        }

        console.log(submitList)
        if (submitList.length > 0) submitEdition(submitList)
    }

    return (
        <>
            <h1 className='mb-5'>Settings</h1>
            <Row className='d-flex mb-2' lg={3}>
                {
                    generalMessage.length > 0 && (
                        <Alert variant="info">{generalMessage}</Alert>
                    )
                }
            </Row >
            <Tabs
                defaultActiveKey="general"
                id="uncontrolled-tab-example"
                className="mb-3"
            >
                <Tab eventKey="general" title="General">
                    <FormContainer>
                        <Form onSubmit={handleSubmit}>
                            <Form.Group as={Row} className="mb-3" controlId="formPlaintextSiteName">
                                <Form.Label column sm="5" lg={3}>
                                    Site Name
                                </Form.Label>
                                <Col sm="7">
                                    <Form.Control
                                        type="text"
                                        value={settings['SITE_NAME']?.value}
                                        onChange={e => {
                                            setSettings({
                                                ...settings,
                                                'SITE_NAME': {
                                                    ...settings['SITE_NAME'],
                                                    value: e.target.value
                                                }
                                            })
                                        }}
                                    />
                                </Col>
                            </Form.Group>

                            <Form.Group as={Row} className="mb-3" controlId="formPlaintextCopyright">
                                <Form.Label column sm="5" lg={3}>
                                    Copyright
                                </Form.Label>
                                <Col sm="7">
                                    <Form.Control type="text"
                                        value={settings['COPYRIGHT']?.value}
                                        onChange={e => {
                                            setSettings({
                                                ...settings,
                                                'COPYRIGHT': {
                                                    ...settings['COPYRIGHT'],
                                                    value: e.target.value
                                                }
                                            })
                                        }}
                                    />
                                </Col>
                            </Form.Group>
                            <Form.Group as={Row} className="mb-3" controlId="formPlaintextSiteLogo">
                                <Form.Label column sm="5" lg={3}>
                                    Site Logo
                                </Form.Label>
                                <Col sm="7">
                                    {settings['SITE_LOGO'] && (
                                        <Image
                                            src={`http://localhost:8080/settings-service/photos/${settings['SITE_LOGO']?.value}`}
                                        ></Image>
                                    )}
                                </Col>
                            </Form.Group>
                            <Form.Group as={Row} className="mb-3" controlId="Locale">
                                <Form.Label column sm="5" lg={3}>
                                    Currency
                                </Form.Label>
                                <Col sm="7">
                                    <Form.Select aria-label="CURRENCY"
                                        onChange={e => {
                                            // const currencyArr = settings['CURRENCY'].value.split(',')
                                            const selectedCurrency = currencies.content
                                                .filter(currency => (currency.language + "," + currency.country) === e.target.value)[0]

                                            console.log(selectedCurrency)
                                            setSettings({
                                                ...settings,
                                                'CURRENCY': {
                                                    ...settings['CURRENCY'],
                                                    value: e.target.value
                                                },
                                            })
                                        }}>
                                        {currencies?.content &&
                                            currencies.content.map(currency => (
                                                <option key={currency.id}
                                                    value={`${currency.language},${currency.country}`}
                                                    selected={
                                                        `${currency.language},${currency.country}` ===
                                                        settings['CURRENCY']?.value
                                                    }>
                                                    {currency.name} - {currency.code} - {currency.symbol}
                                                </option>
                                            ))
                                        }
                                    </Form.Select>
                                </Col>
                            </Form.Group>
                            {/* <Form.Group as={Row}
                                className="mb-3"
                                controlId="formPlaintextCurrencySymbolPosition"
                            >
                                <Form.Label column sm="5" lg={3}>
                                    Currency Symbol Position
                                </Form.Label>
                                <Col sm="7">
                                    <Form.Select aria-label="Currency Symbol position"
                                        onChange={e => {
                                            setSettings({
                                                ...settings,
                                                'CURRENCY_SYMBOL_POSITION': {
                                                    ...settings['CURRENCY_SYMBOL_POSITION'],
                                                    value: e.target.value
                                                }
                                            })
                                        }}>
                                        <option value="BEFORE"
                                            selected={"BEFORE" === settings['CURRENCY_SYMBOL_POSITION']?.value}
                                        >BEFORE</option>
                                        <option value="AFTER"
                                            selected={"AFTER" === settings['CURRENCY_SYMBOL_POSITION']?.value}
                                        >AFTER</option>
                                    </Form.Select>
                                </Col>
                            </Form.Group> */}
                            {/* <Form.Group as={Row} className="mb-3" controlId="decimalPointType">
                                <Form.Label column sm="5" lg={3}>
                                    Decimal Point Type
                                </Form.Label>
                                <Col sm="7">
                                    <Form.Select aria-label="Decimal point type"
                                        onChange={e => {
                                            setSettings({
                                                ...settings,
                                                'DECIMAL_POINT_TYPE': {
                                                    ...settings['DECIMAL_POINT_TYPE'],
                                                    value: e.target.value
                                                }
                                            })
                                        }}>
                                        <option value="POINT"
                                            selected={"POINT" === settings['DECIMAL_POINT_TYPE']?.value}
                                        >POINT</option>
                                        <option value="COMMA"
                                            selected={"COMMA" === settings['DECIMAL_POINT_TYPE']?.value}
                                        >COMMA</option>
                                    </Form.Select>
                                </Col>
                            </Form.Group> */}
                            {/* <Form.Group as={Row} className="mb-3" controlId="decimalDigits">
                                <Form.Label column sm="5" lg={3}>
                                    Decimal Digits
                                </Form.Label>
                                <Col sm="7">
                                    <Form.Select aria-label="Decimal DIGITS"
                                        onChange={e => {
                                            setSettings({
                                                ...settings,
                                                'DECIMAL_DIGITS': {
                                                    ...settings['DECIMAL_DIGITS'],
                                                    value: e.target.value
                                                }
                                            })
                                        }}>
                                        <option value="0"
                                            selected={"0" === settings['DECIMAL_DIGITS']?.value}
                                        >0</option>
                                        <option value="1"
                                            selected={"1" === settings['DECIMAL_DIGITS']?.value}
                                        >1</option>
                                        <option value="2"
                                            selected={"2" === settings['DECIMAL_DIGITS']?.value}
                                        >2</option>
                                    </Form.Select>
                                </Col>
                            </Form.Group> */}
                            {/* <Form.Group as={Row} className="mb-3" controlId="thousandsPointType">
                                <Form.Label column sm="5" lg={3}>
                                    Thousands Point Type
                                </Form.Label>
                                <Col sm="7">
                                    <Form.Select aria-label="Thousands point type"
                                        onChange={e => {
                                            setSettings({
                                                ...settings,
                                                'THOUSANDS_POINT_TYPE': {
                                                    ...settings['THOUSANDS_POINT_TYPE'],
                                                    value: e.target.value
                                                }
                                            })
                                        }}>
                                        <option value="POINT"
                                            selected={"POINT" === settings['THOUSANDS_POINT_TYPE']?.value}
                                        >POINT</option>
                                        <option value="COMMA"
                                            selected={"COMMA" === settings['THOUSANDS_POINT_TYPE']?.value}
                                        >COMMA</option>
                                    </Form.Select>
                                </Col>
                            </Form.Group> */}
                            <Form.Group className='d-flex justify-content-center'>
                                <Button type="submit" variant='primary' className='my-3' disabled={isLoading}>
                                    Save
                                </Button>
                            </Form.Group>
                        </Form>
                    </FormContainer >
                </Tab>
                <Tab eventKey="countries" title="Countries">
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
                                <option key={c.id}
                                    selected={country && c.id === country.id}
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
                <Tab eventKey="states" title="States">
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
                <Tab eventKey="mail_server" title="Mail Server">
                    Tab content for Contact
                </Tab>
                <Tab eventKey="mail_template" title="Mail Template">
                    Tab content for Contact
                </Tab>
                <Tab eventKey="payment" title="Payment">
                    Tab content for Contact
                </Tab>
            </Tabs >
            <Modal show={show} onHide={handleModalClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Delete</Modal.Title>
                </Modal.Header>
                <Modal.Body>Do you want to Delete a user with {targetBrand.name}</Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleModalClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={deleteUserHandler}>
                        Delete User
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}

export default SettingsListScreen