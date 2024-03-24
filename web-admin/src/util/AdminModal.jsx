import React from 'react'
import { Warning } from '@mui/icons-material'

function AdminModal({ handleModalClose, deleteHandler, title, content }) {
    return (
        <div onClick={handleModalClose}
            className='fixed left-0 top-0 bg-black bg-opacity-50 w-screen h-screen flex justify-center items-center'>
            <div className='bg-white rounded shadow-md p-8 w-[35%]'>
                <div className='flex justify-center gap-2'>
                    <div className='bg-red-100 rounded-full text-red-600 w-8 h-8 flex items-center justify-center'>
                        <Warning />
                    </div>
                    <div className='flex-grow'>
                        <h1 className="font-bold text-lg mb-2">{title}</h1>
                        <p>{content}</p>
                    </div>
                </div>
                <div className='mt-10 flex justify-end gap-2'>
                    <button className='btn' onClick={handleModalClose} >취소</button>
                    <button className='btn' onClick={deleteHandler}>확인</button>
                </div>
            </div>
        </div>
    )
}

export default AdminModal