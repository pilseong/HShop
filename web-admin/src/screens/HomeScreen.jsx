import { Box, Button, Typography } from '@mui/material'
import React from 'react'
import { Link } from 'react-router-dom'


function HomeScreen() {
    return (
        <>
            <h1 className="text-4xl m-5">메인 페이지</h1>
            <Link to="/users/new">
                <Box m={5}>
                    <Button type="button">Create User</Button>
                </Box>
            </Link>
        </>
    )
}

export default HomeScreen