import { Navbar, Container, Nav, Row, NavDropdown } from 'react-bootstrap'
import { FaShoppingCart, FaUser } from 'react-icons/fa'
import { SiBrandfolder } from "react-icons/si";
import { MdCategory } from "react-icons/md";
import { FaUserGroup } from "react-icons/fa6";
import { MdOutlineProductionQuantityLimits } from "react-icons/md";
import { AiFillCustomerService } from "react-icons/ai";
import { BiPurchaseTag } from "react-icons/bi";
import { FaShippingFast } from "react-icons/fa";
import { MdArticle } from "react-icons/md";
import { FcSalesPerformance } from "react-icons/fc";
import { CiSettings } from "react-icons/ci";
import { LinkContainer } from 'react-router-bootstrap'
import logo from '../assets/hshop.png'
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { useLogoutMutation } from '../slices/usersApiSlice';
import { logout } from '../slices/authSlice'
import { useState } from 'react';

function Header() {
    // const { cartItems } = useSelector(state => state.cart)
    const { userInfo } = useSelector(state => state.auth)
    const dispatch = useDispatch()
    const navigate = useNavigate()

    const [keyword, setKeyword] = useState('')

    const [logoutApi] = useLogoutMutation()

    const logoutHandler = async () => {
        try {
            await logoutApi().unwrap()
            dispatch(logout())
            navigate('/')
        } catch (error) {
            console.log(error)
        }
    }

    const handleSearch = (e) => {
        // alert()
        e.preventDefault()
        navigate(`/search/${keyword}`)
    }

    return (
        <header>
            <Navbar bg="dark" variant="dark" expand="lg" collapseOnSelect>
                <Container>
                    <LinkContainer to="/">
                        <Navbar.Brand>
                            <img src={logo} alt="HShop" />
                        </Navbar.Brand>
                    </LinkContainer>
                    <Navbar.Toggle aria-controls='basic-navbar-nav' />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <form className="d-flex" role="search" onSubmit={handleSearch}>
                            <input className="form-control me-2"
                                onChange={(e) => setKeyword(e.target.value)}
                                type="search"
                                value={keyword}
                                placeholder="Search" aria-label="Search" />
                            <button className="btn btn-outline-success" type="submit">Search</button>
                        </form>
                        <Nav className='ms-auto'>
                            {
                                userInfo ? (
                                    <NavDropdown title={`${userInfo.firstName}님`} id="username">
                                        <LinkContainer to='/profile'>
                                            <NavDropdown.Item>프로필</NavDropdown.Item>
                                        </LinkContainer>
                                        <LinkContainer to='/login'>
                                            <NavDropdown.Item
                                                onClick={logoutHandler}
                                            >로그아웃</NavDropdown.Item>
                                        </LinkContainer>
                                    </NavDropdown>
                                ) : (
                                    <LinkContainer to="/login">
                                        <Nav.Link className='d-flex align-items-center'>
                                            <FaUser className='me-1' />로그인
                                        </Nav.Link>
                                    </LinkContainer>
                                )
                            }
                            {
                                userInfo ? (
                                    <LinkContainer to="/register">
                                        <Nav.Link className='d-flex align-items-center'>
                                            <FaShoppingCart className='me-1' />카트
                                        </Nav.Link>
                                    </LinkContainer>
                                ) : (
                                    <LinkContainer to="/register">
                                        <Nav.Link>
                                            회원가입
                                        </Nav.Link>
                                    </LinkContainer>
                                )
                            }

                            <LinkContainer to="/help">
                                <Nav.Link>
                                    고객센터
                                </Nav.Link>
                            </LinkContainer>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </header >
    )
}

export default Header