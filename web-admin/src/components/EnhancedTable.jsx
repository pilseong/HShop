import * as React from 'react';

import TablePagination from '@mui/material/TablePagination';

import { Cancel, Check, CheckCircle, Delete, Edit, Person } from '@mui/icons-material';
import { TbMinusVertical } from 'react-icons/tb';
import { Link } from 'react-router-dom';
import ManagementColumn from './ManagementColumn';

function EnhancedTableHead(props) {
  const { headCells } =
    props;

  return (
    <thead>
      <tr className='border-b-2'>
        {headCells.map((headCell) => (
          <td className="p-4 "
            key={headCell.id}
            align="center"
          >
            <div className="text-gray-700 font-bold">{headCell.label}</div>
          </td>
        ))}
      </tr>
    </thead >
  );
}

export default function EnhancedTable({
  handleModalShow,
  data, navigatePage,
  total, page, size, headCells, setTarget }
) {

  const createRow = (row) => {
    if (row) {
      return headCells.map(cell => {
        const cellpath = cell.id.split('.')
        let element =
          (cellpath.length == 2) ?
            row[cellpath[0]][cellpath[1]] :
            row[cell.id]

        switch (cell.type) {
          case 'string': {
            // 문자열 배역
            if (Array.isArray(element)) {
              return (
                <td align="center" key={`${row.id}-${cell.id}`}>
                  {
                    element.map(roles => roles.name)
                      .map(name => (<div className={cell.id === 'categories' ? 'badge' : 'text-sm'} key={name}>{name}</div>))
                  }
                </td>
              )
            } else {
              return (
                <td key={`${row.id}-${cell.id}`} align="center" className={cell.type === 'id' ? 'text-xs' : 'text-sm'}>{element}</td>
              )
            }
          }

          case 'image': {
            return (
              <td align="center" className='p-2' key={`${row.id}-${cell.id}`}>
                {row[cell.id]
                  ? (<img className="object-fill shadow-md"
                    width={120}
                    src={`${cell.path}${row.id}/${row[cell.id]}`} />)
                  : (
                    <Person />
                  )
                }
              </td>
            )
          }

          case 'checkbox': {
            return (
              <td align="center" className="p-2" key={`${row.id}-${cell.id}`}>
                {
                  row[cell.id] === 'ACTIVE' ? (<Check className='text-green-500' />) : (<Cancel className='text-red-500' />)
                }
              </td>
            )
          }
        }
      })
    }
  }

  return (
    <div>
      <div>
        <div>
          <table className="w-full table-auto">
            <EnhancedTableHead headCells={headCells} />
            <tbody>
              {data.map((row, index) => {
                return (
                  <tr
                    key={`${row.id}-tr`}
                    className="border-b"
                  >
                    {
                      createRow(row)
                    }

                    <ManagementColumn
                      key={`${row.id}-management`}
                      row={row}
                      handleModalShow={handleModalShow}
                      setTarget={setTarget}
                      path={`${headCells[headCells.length - 1].path}${row.id}`} />
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
        <div>

        </div>
        <TablePagination
          component="div"
          count={total}
          page={page}
          onPageChange={(event, page) => {
            navigatePage(page)
          }}
          rowsPerPage={size}
          onRowsPerPageChange={(e) => {
            navigatePage(0, e.target.value)
          }}
        />
      </div>
    </div>
  );
}
