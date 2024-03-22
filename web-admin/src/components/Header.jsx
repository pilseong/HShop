import { Navbar, Container, Nav, NavDropdown } from 'react-bootstrap'
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
import logo from '../assets/hshop.jpeg'
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { logout } from '../slices/authSlice'
import { useLogoutMutation } from '../slices/usersApiSlice';

function Header() {

    const { userInfo } = useSelector(state => state.auth)

    const dispatch = useDispatch()
    const navigate = useNavigate()
    const [logoutApi] = useLogoutMutation()

    const logoutHandler = async () => {
        try {
            const res = await logoutApi()
            dispatch(logout())
            navigate('/login')

            return
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <header>
            <Navbar bg="dark" variant="dark" expand="md" collapseOnSelect>
                <Container>
                    <LinkContainer to="/">
                        <Navbar.Brand>
                            <img src={logo} alt="HShop" />
                        </Navbar.Brand>
                    </LinkContainer>
                    <Navbar.Toggle aria-controls='basic-navbar-nav' />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className='ms-auto'>
                            {
                                userInfo && (
                                    <>
                                        {userInfo.roles.split(',').findIndex(role => role === 'Admin') >= 0 && (
                                            <LinkContainer to="/users">
                                                <Nav.Link>
                                                    <FaUserGroup /> Users
                                                </Nav.Link>
                                            </LinkContainer>
                                        )}
                                        <LinkContainer to="/categories">
                                            <Nav.Link>
                                                <MdCategory /> Categories
                                            </Nav.Link>
                                        </LinkContainer>
                                        <LinkContainer to="/brands">
                                            <Nav.Link>
                                                <SiBrandfolder /> Brands
                                            </Nav.Link>
                                        </LinkContainer>
                                        <LinkContainer to="/products">
                                            <Nav.Link>
                                                <MdOutlineProductionQuantityLimits /> Products
                                            </Nav.Link>
                                        </LinkContainer>
                                        <LinkContainer to="/customers">
                                            <Nav.Link>
                                                <AiFillCustomerService /> Customers
                                            </Nav.Link>
                                        </LinkContainer>
                                        <LinkContainer to="/cart">
                                            <Nav.Link>
                                                <FaShippingFast /> Shipping
                                            </Nav.Link>
                                        </LinkContainer>
                                        <LinkContainer to="/cart">
                                            <Nav.Link>
                                                <BiPurchaseTag /> Orders
                                            </Nav.Link>
                                        </LinkContainer>
                                        <LinkContainer to="/cart">
                                            <Nav.Link>
                                                <FcSalesPerformance /> Sales Report
                                            </Nav.Link>
                                        </LinkContainer>
                                        <LinkContainer to="/cart">
                                            <Nav.Link>
                                                <MdArticle /> Articles
                                            </Nav.Link>
                                        </LinkContainer>
                                        <LinkContainer to="/settings">
                                            <Nav.Link>
                                                <CiSettings /> Settings
                                            </Nav.Link>
                                        </LinkContainer>
                                    </>
                                )}
                            {userInfo ? (
                                <NavDropdown title={userInfo.firstName} id="username">
                                    <LinkContainer to='/profile'>
                                        <NavDropdown.Item>Profile</NavDropdown.Item>
                                    </LinkContainer>
                                    <LinkContainer to='/login'>
                                        <NavDropdown.Item
                                            onClick={logoutHandler}
                                        >Logout</NavDropdown.Item>
                                    </LinkContainer>
                                </NavDropdown>
                            ) : (
                                <LinkContainer to="/login">
                                    <Nav.Link>
                                        <FaUser /> Sign in
                                    </Nav.Link>
                                </LinkContainer>
                            )}
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </header >
    )
}

export default Header