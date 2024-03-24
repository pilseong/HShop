import React from 'react'
import { Link } from 'react-router-dom'
import { Delete, Edit } from '@mui/icons-material';
import { TbMinusVertical } from 'react-icons/tb';

function ManagementColumn({ handleModalShow, setTarget, path, row }) {
    return (
        <td>
            <div className="flex items-center justify-center">
                <Link to={path}><Edit /></Link>
                <TbMinusVertical />
                <Delete className="cursor-pointer"
                    onClick={() => {
                        setTarget(row)
                        handleModalShow()
                    }}
                />
            </div>
        </td>
    )
}

export default ManagementColumn