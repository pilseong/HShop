import { Box, CircularProgress } from "@mui/material";
import { Spinner } from "react-bootstrap"

const Loader = () => {
    return (
        <Box sx={{
            display: 'flex',
            justifyContent: "center",
            marginTop: 10
        }}>
            <CircularProgress />
        </Box>
        // <Spinner
        //     animation="border"
        //     role="status"
        //     style={{
        //         width: "100px",
        //         height: "100px",
        //         margin: "auto",
        //         display: "block"
        //     }}
        // ></Spinner >
    )
}

export default Loader;