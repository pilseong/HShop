import * as React from 'react';

import Box from '@mui/material/Box';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import TableSortLabel from '@mui/material/TableSortLabel';
import Paper from '@mui/material/Paper';
import FormControlLabel from '@mui/material/FormControlLabel';
import Switch from '@mui/material/Switch';

import { visuallyHidden } from '@mui/utils';
import { CheckCircle, Delete, Edit, Image, Person, PlayCircleFilled } from '@mui/icons-material';
import { LinkContainer } from 'react-router-bootstrap';
import { Button, Typography } from '@mui/material';
import { TbMinusVertical } from 'react-icons/tb';

function EnhancedTableHead(props) {
    const { onSelectAllClick, order, orderBy, numSelected, rowCount, onRequestSort, headCells } =
        props;
    const createSortHandler = (property) => (event) => {
        onRequestSort(event, property);
    };

    return (
        <TableHead>
            <TableRow>
                {headCells.map((headCell) => (
                    <TableCell
                        key={headCell.id}
                        align="center"
                        padding={headCell.disablePadding ? 'none' : 'normal'}
                        sortDirection={orderBy === headCell.id ? order : false}
                    >
                        <TableSortLabel
                            active={orderBy === headCell.id}
                            direction={orderBy === headCell.id ? order : 'asc'}
                            onClick={createSortHandler(headCell.id)}
                        >
                            <Typography fontWeight={700}>{headCell.label}</Typography>
                            {orderBy === headCell.id ? (
                                <Box component="span" sx={visuallyHidden}>
                                    {order === 'desc' ? 'sorted descending' : 'sorted ascending'}
                                </Box>
                            ) : null}
                        </TableSortLabel>
                    </TableCell>
                ))}
            </TableRow>
        </TableHead>
    );
}


export default function EnhancedTable({ data, navigatePage, total, page, size, headCells }) {
    const [order, setOrder] = React.useState('asc');
    const [orderBy, setOrderBy] = React.useState('calories');
    const [selected, setSelected] = React.useState([]);

    const handleRequestSort = (event, property) => {
        const isAsc = orderBy === property && order === 'asc';
        setOrder(isAsc ? 'desc' : 'asc');
        setOrderBy(property);
    };

    const handleClick = (event, id) => {
        const selectedIndex = selected.indexOf(id);
        let newSelected = [];

        if (selectedIndex === -1) {
            newSelected = newSelected.concat(selected, id);
        } else if (selectedIndex === 0) {
            newSelected = newSelected.concat(selected.slice(1));
        } else if (selectedIndex === selected.length - 1) {
            newSelected = newSelected.concat(selected.slice(0, -1));
        } else if (selectedIndex > 0) {
            newSelected = newSelected.concat(
                selected.slice(0, selectedIndex),
                selected.slice(selectedIndex + 1),
            );
        }
        setSelected(newSelected);
    };

    const visibleRows = data

    return (
        <Box sx={{ width: '100%' }}>
            <Paper sx={{ width: '100%', mb: 2 }}>
                <TableContainer>
                    <Table
                        sx={{ minWidth: 750 }}
                        aria-labelledby="tableTitle"
                    >
                        <EnhancedTableHead
                            order={order}
                            orderBy={orderBy}
                            onRequestSort={handleRequestSort}
                            headCells={headCells}
                        />
                        <TableBody>
                            {visibleRows.map((row, index) => {
                                const labelId = `enhanced-table-checkbox-${index}`
                                return (
                                    <TableRow
                                        hover
                                        onClick={(event) => handleClick(event, row.id)}
                                        role="checkbox"
                                        tabIndex={-1}
                                        key={row.id}
                                        sx={{ cursor: 'pointer' }}
                                    >
                                        <TableCell
                                            component="th"
                                            id={row.id}
                                            scope="row"
                                            padding="none"
                                        >
                                            {row.id}
                                        </TableCell>
                                        <TableCell align="center">
                                            {row.photo
                                                ? (<Image width={120} src={`http://localhost:8080/user-service/photos/${row.id}/${row.photo}`} />)
                                                : (
                                                    <Person />
                                                )

                                            }

                                        </TableCell>
                                        <TableCell align="center">{row.email}</TableCell>
                                        <TableCell align="center">{row.firstName}</TableCell>
                                        <TableCell align="center">{row.lastName}</TableCell>
                                        <TableCell align="center">
                                            {
                                                row.roles.map(roles => roles.name)
                                                    .map(name => (<div key={name}>{name}</div>))
                                            }
                                        </TableCell>
                                        <TableCell align='center'>
                                            <CheckCircle
                                                sx={{ color: row.status === 'ACTIVE' ? 'green' : 'gray' }}
                                            />
                                        </TableCell>
                                        <TableCell>
                                            <LinkContainer to={`/users/${row.id}`}>
                                                <Edit />
                                            </LinkContainer>
                                            <TbMinusVertical />
                                            <Delete
                                                onClick={() => {
                                                    handleModalShow()
                                                    setTargetUser(user)
                                                }}
                                            />

                                        </TableCell>
                                    </TableRow>
                                );
                            })}
                        </TableBody>
                    </Table>
                </TableContainer>
                <TablePagination
                    sx={{
                        '& .Mui-selected': {
                            backgroundColor: '#f0e3c1',
                            color: 'white',
                            opacity: 0.8
                        }, "& .MuiPaginationItem-root": {
                            color: "black",
                            fontFamily: 'Montserrat',
                        }
                    }}
                    component="div"
                    count={total}
                    page={page}
                    onPageChange={(event, page) => {
                        console.log(page)
                        navigatePage(page)
                    }}
                    rowsPerPage={size}
                    onRowsPerPageChange={(e) => {
                        navigatePage(0, e.target.value)
                    }}
                />
            </Paper>
        </Box >
    );
}
