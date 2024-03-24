import React from 'react'
import { LinkContainer } from 'react-router-bootstrap'
import logo from '../assets/hshop.jpeg'
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { logout } from '../slices/authSlice'
import { useLogoutMutation } from '../slices/usersApiSlice';
import { AppBar, Avatar, Box, Button, Container, IconButton, Menu, MenuItem, Toolbar, Tooltip, Typography, styled } from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu'

const Brand = styled('div')(({ theme }) => ({
    display: "none",
    [theme.breakpoints.up("md")]: {
        display: "flex"
    },
    marginRight: 20
}))


function Header() {
    const { userInfo } = useSelector(state => state.auth)

    const dispatch = useDispatch()
    const navigate = useNavigate()
    const [logoutApi] = useLogoutMutation()

    const logoutHandler = async () => {
        try {
            handleCloseNavMenu()
            handleCloseUserMenu()
            const res = await logoutApi()
            dispatch(logout())
            navigate('/login')
            return
        } catch (error) {
            console.log(error)
        }
    }

    const [anchorElNav, setAnchorElNav] = React.useState(null);
    const [anchorElUser, setAnchorElUser] = React.useState(null);

    const handleOpenNavMenu = (event) => {
        setAnchorElNav(event.currentTarget);
    };
    const handleOpenUserMenu = (event) => {
        setAnchorElUser(event.currentTarget);
    };

    const handleCloseNavMenu = () => {
        setAnchorElNav(null);
    };

    const handleCloseUserMenu = () => {
        setAnchorElUser(null);
    };

    // 나중에 DB에서 불러 오도록 수정
    const pages = [
        {
            name: "Users",
            link: "/users"
        },
        {
            name: "Categories",
            link: "/categories"
        },
        {
            name: "Brands",
            link: "/brands"
        },
        {
            name: "Products",
            link: "/products"
        },
        {
            name: "Customers",
            link: "/customers"
        },
        {
            name: "Orders",
            link: "/orders"
        },
        {
            name: "Shipping",
            link: "/shipping"
        },
        {
            name: "Sales Report",
            link: "/reports"
        },
        {
            name: "Settings",
            link: "/settings"
        },
    ]


    return (
        <AppBar position="static">
            <Container maxWidth="xl">
                <Toolbar disableGutters>
                    <Brand><img src={logo} /></Brand>
                    {userInfo &&
                        <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
                            <IconButton
                                size="large"
                                aria-haspopup="true"
                                onClick={handleOpenNavMenu}
                                color="inherit"
                            >
                                <MenuIcon />
                            </IconButton>

                            <Menu
                                id="menu-appbar"
                                anchorEl={anchorElNav}
                                anchorOrigin={{
                                    vertical: 'bottom',
                                    horizontal: 'left',
                                }}
                                keepMounted
                                transformOrigin={{
                                    vertical: 'top',
                                    horizontal: 'left',
                                }}
                                open={Boolean(anchorElNav)}
                                onClose={handleCloseNavMenu}
                                sx={{
                                    display: { xs: 'block', md: 'none' },
                                }}
                            >
                                {pages.map(page => (
                                    <LinkContainer key={page.name} to={page.link}>
                                        <MenuItem key={page.name} onClick={handleCloseNavMenu}>
                                            <Typography textAlign="center">{page.name}</Typography>
                                        </MenuItem>
                                    </LinkContainer>
                                ))}
                            </Menu>
                        </Box>
                    }
                    <Box sx={{ display: { xs: 'flex', md: 'none' }, mr: 1 }}>
                        <img src={logo} />
                    </Box>
                    <Typography
                        variant="h5"
                        noWrap
                        component="a"
                        href="#app-bar-with-responsive-menu"
                        sx={{
                            mr: 2,
                            display: { xs: 'flex', md: 'none' },
                            flexGrow: 1,
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            letterSpacing: '.3rem',
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        HSHOP
                    </Typography>
                    <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
                        {userInfo && pages.map(page => (
                            <LinkContainer key={page.name} to={page.link}>
                                <Button
                                    key={page.name}
                                    onClick={handleCloseNavMenu}
                                    sx={{ my: 2, color: 'white', display: 'block' }}
                                >
                                    {page.name}
                                </Button>
                            </LinkContainer>

                        ))}
                    </Box>

                    <Box sx={{ flexGrow: 0 }}>
                        {userInfo ? (
                            <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                                <Avatar alt={userInfo.profile.firstName}
                                    src={userInfo?.profile?.photo && `http://localhost:8080/user-service/photos/${userInfo?.profile?.id}/${userInfo?.profile?.photo}`}>
                                    {userInfo.profile.firstName.charAt(0)}
                                </Avatar>


                            </IconButton>
                        ) : (
                            <Box>Register</Box>
                        )}

                        <Menu
                            sx={{ mt: '45px' }}
                            id="menu-appbar"
                            anchorEl={anchorElUser}
                            anchorOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            open={Boolean(anchorElUser)}
                            onClose={handleCloseUserMenu}
                        >
                            {userInfo && (
                                [
                                    <LinkContainer key="profile" to="/profile">
                                        <MenuItem key="profile" onClick={handleCloseUserMenu}>
                                            <Typography textAlign="center">Profile</Typography>
                                        </MenuItem>
                                    </LinkContainer>,
                                    <LinkContainer key="logout" to="/login">
                                        <MenuItem key="logout" onClick={logoutHandler}>
                                            <Typography textAlign="center">Logout</Typography>
                                        </MenuItem>
                                    </LinkContainer>
                                ]
                            )}


                        </Menu>
                    </Box>
                </Toolbar>
            </Container>
        </AppBar>
    );
}

export default Header