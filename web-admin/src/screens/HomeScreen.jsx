import { Box, Button, Typography } from '@mui/material'
import React from 'react'
import { LinkContainer } from 'react-router-bootstrap'

function HomeScreen() {
    return (
        <>
            <Typography variant='h3' m={5}>HomeScreen</Typography>
            <LinkContainer to="/users/new">
                <Box m={5}>
                    <Button type="button">Create User</Button>
                </Box>
            </LinkContainer>
        </>
    )
}

export default HomeScreen