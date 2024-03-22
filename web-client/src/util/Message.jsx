import React, { useEffect, useState } from 'react';
import { Alert } from 'react-bootstrap';

const Message = ({ variant, children }) => {
    // the alert is displayed by default
    const [alert, setAlert] = useState(true);

    useEffect(() => {
        // when the component is mounted, the alert is displayed for 3 seconds
        const timer = setTimeout(() => {
            setAlert(false);
        }, 3000);
        return () => clearTimeout(timer);
    }, []);

    return (
        <>
            {alert && <Alert variant={variant}>{children}</Alert>}
        </>
    )
}

export default Message