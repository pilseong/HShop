import React from 'react'
import { Button } from 'react-bootstrap'
import { LinkContainer } from 'react-router-bootstrap'

function HomeScreen() {
    return (
        <>
            <h1>HomeScreen</h1>
            <LinkContainer to="/users/new">
                <Button type="button">Create User</Button>
            </LinkContainer>
        </>
    )
}

export default HomeScreen