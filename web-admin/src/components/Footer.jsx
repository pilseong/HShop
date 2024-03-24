import { Box, Container, Typography } from '@mui/material'
import React from 'react'


function Footer() {
    const currentYear = new Date().getFullYear()
    return (
        <footer>
            <Container>
                <Box textAlign="center" my={5}>
                    <Typography variant='h6'>HShop &copy; {currentYear}</Typography>
                </Box>
            </Container>
        </footer >
    )
}

export default Footer