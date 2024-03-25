import React from 'react'
import { Alert, Form, Row } from 'react-bootstrap'
import { useEffect, useState } from 'react'
import axios from 'axios'
import Message from '../util/Message'
import { Link } from 'react-router-dom';
import { useSearchProductsMutation } from '../slices/productsApiSlice';
import Loader from '../components/Loader';
import EnhancedTable from '../components/EnhancedTable';


function ProductListScreen() {

  const [showModal, setShowModal] = useState(false);
  const [targetProduct, setTargetProduct] = useState({})
  const [message, setMessage] = useState("")
  const [currentPage, setCurrentPage] = useState(0)
  const [total, setTotal] = useState(0)
  const [pageSize, setPageSize] = useState(0)
  const [orderBy, setOrderBy] = useState('asc')
  const [sortBy, setSortBy] = useState('name')
  const [keyword, setKeyword] = useState("")
  const [cleared, setCleared] = useState(0)


  // 유저리스트
  const [products, setProducts] = useState([])
  const [error, setError] = useState({
    code: "",
    message: ""
  })

  const [searchProducts, { isLoading: isProductLoading }] = useSearchProductsMutation()

  // clear 버튼을 눌렀을 때 keyword가 클리어 된 후에 실행되도록 추가
  useEffect(() => {
    if (cleared > 0)
      fetchProducts(0, 'asc', 'name')
  }, [cleared])

  useEffect(() => {
    fetchProducts(0)
  }, [])

  const fetchProducts = async (page = 0, order = 'asc', sort = 'name', size = 10) => {
    try {
      const { data } = await searchProducts({
        query: keyword,
        pageNo: page,
        pageSize: 10,
        sortBy: sort,
        orderBy: order
      })

      setProducts(data.content)
      setCurrentPage(data.pageNo)
      setOrderBy(order)
      setPageSize(data.pageSize)
      setTotal(data.totalElements)
      setSortBy(sort)

    } catch (error) {
      console.log(error)
    }
  }

  const exportToCSV = async () => {
    try {
      const result = await axios.post(
        `/api/products/export/csv?pageNo=${currentPage}&pageSize=10&sortBy=${sortBy}&orderBy=${orderBy}`, {
        userId: "9b93b0f0-7a18-4e8a-9b2e-cb501e7394a3",
        keyword: `${keyword}`
      }, {
        headers: {
          "Content-Type": "application/json",
          "Accept": "text/csv"
        }
      });


      // cvs를 받아서 바로 다운로드 하는 기능
      const filename = result.headers
        .get("Content-Disposition")
        .split('=')[1]

      console.log(filename)

      const url = window.URL.createObjectURL(
        new Blob([result.data]),
      );

      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', `${filename}`);

      // Append to html link element page
      document.body.appendChild(link);

      // Start download
      link.click();

      // Clean up and remove the link
      link.parentNode.removeChild(link);

    } catch (error) {
      console.log(error)
    }
  }

  const handleModalClose = () => {
    setShowModal(false)
  }

  const deleteProductHandler = async () => {
    try {
      await axios.delete(`/api/products/${targetProduct.id}`)
      await fetchProducts(currentPage, orderBy, sortBy)

      showMessage(`user with ${targetProduct.name} has been deleted successfully`)

    } catch (error) {
      console.log(error)
      setError(error.response.data)
    }
    setShowModal(false);
  }

  const handleModalShow = () => setShowModal(true);


  // 수정 실행 메소드
  const submitEdition = async (product) => {
    try {
      // 데이터 생성
      const formData = new FormData();
      formData.append("command", new Blob([JSON.stringify(product)], {
        type: "application/json",
      }));

      const result = await axios.post('/api/products/edit', formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        }
      })
      console.log(result)
      showMessage(`product with ${product.name} has been upated successfully`)
      await fetchProducts(currentPage, orderBy, sortBy)
    } catch (error) {
      console.log(error)
      setError(error.response.data)
      return
    }
  }

  const handleActiveToggle = async (product) => {

    // 서버에 변경, 패스워드 공백은 검증로직 예외
    await submitEdition({
      ...product,
      password: '',
      status: product.status === 'ACTIVE' ? 'INACTIVE' : 'ACTIVE',
      roles: product.roles.map(r => r.id)
    })

    //     // 목록 업데이트
    //     // let updatedproducts = products.filter(u => u.id !== product.id)
    //     // updatedproducts.push({ ...product, enabled: !product.enabled })

    //     // setProducts(updatedProducts)
  }

  const navigatePage = (page, size = pageSize) => {
    fetchProducts(page, orderBy, sortBy, size)
  }

  const showMessage = (msg) => {
    setMessage(msg)

    setTimeout(() => {
      setMessage("");
    }, 3000);

  }
  return (
    <div className='text-gray-600'>
      {
        isProductLoading ? (<Loader></Loader>) : (
          <>
            <h3 className='my-6 text-4xl'>제품 목록</h3>
            <div className="flex gap-2">
              <input type="text"
                onKeyDown={e => {
                  if (e.key === 'Enter') {
                    fetchProducts(0, 'asc', 'name')
                  }
                }}
                onChange={e => {
                  setKeyword(e.target.value)
                }}
                value={keyword}
                placeholder="Enter keyword" className="bg-gray-100 p-2" />
              <button className='btn'
                onClick={() => fetchProducts(0, 'asc', 'name')}
              >Search</button>
              <button className='btn'
                onClick={() => {
                  setKeyword('')
                  setCleared(cleared + 1)
                }}
              >Clear</button>
              <Link className='ml-auto' to="/products/new">
                <button className='btn'>Create Products</button>
              </Link>
            </div>
            {
              error?.code && (
                <Message variant='danger' className='mt-2'>
                  <Form.Text className="text-muted">
                    {
                      error.message
                        .split(',')
                        .map(err => <><strong>{err}</strong><br /></>)
                    }
                  </Form.Text>
                </Message>
              )
            }
            {
              message.length > 0 && (
                <Alert variant="info">{message}</Alert>
              )
            }
            {
              products.length === 0 ? (
                <div className='text-center mt-5'>
                  <h4>검색된 제품이 없습니다.</h4>
                </div>
              ) : (
                <div className='mt-10'>
                  <EnhancedTable
                    handleModalShow={handleModalShow}
                    data={products}
                    navigatePage={navigatePage}
                    total={total}
                    page={currentPage}
                    size={pageSize || 10}
                    setTarget={setTargetProduct}
                    headCells={[
                      {
                        id: 'id',
                        type: 'string',
                        label: 'Product Id',
                      },
                      {
                        id: 'mainImage',
                        type: 'image',
                        label: 'Main Image',
                        path: 'http://localhost:8080/catalog-service/photos/productimages/'
                      },
                      {
                        id: 'name',
                        type: 'string',
                        label: 'Product Name',
                      },
                      {
                        id: 'brand.name',
                        type: 'string',
                        label: 'Brand',
                      },
                      {
                        id: 'category.name',
                        type: 'string',
                        label: 'Category',
                      },
                      {
                        id: 'inventory.amount',
                        type: 'string',
                        label: 'Stock',
                      },
                      {
                        id: 'management',
                        type: 'management',
                        label: '',
                        path: '/brands/'
                      }
                    ]}
                  />
                </div>
                // <Row>
                //   <Table striped bordered hover>
                //     <thead>
                //       <tr>
                //         <th>Product ID</th>
                //         <th>Main image</th>
                //         <th>
                //           <a type="button"
                //             onClick={() => fetchProducts(currentPage, orderBy, 'name')}
                //           >Product Name</a>
                //           {
                //             sortBy === 'name' && (
                //               orderBy === 'asc' ?
                //                 (<BiSolidDownArrow
                //                   type='button'
                //                   onClick={() => { fetchProducts(currentPage, 'desc', 'name') }}
                //                 />) :
                //                 (<BiSolidUpArrow
                //                   type='button'
                //                   onClick={() => { fetchProducts(currentPage, 'asc', 'name') }}
                //                 />)
                //             )
                //           }
                //         </th>
                //         <th>Brand</th>
                //         <th>Category</th>
                //         <th>Stock</th>
                //         <th></th>
                //       </tr>
                //     </thead>
                //     <tbody>
                //       {
                //         products.map(product => (
                //           <tr key={product.id} className='align-middle'>
                //             <td><small>{product.id}</small></td>
                //             <td>
                //               {
                //                 product.mainImage &&
                //                 (
                //                   <Image width={120} thumbnail fluid
                //                     src={`http://localhost:8080/catalog-service/photos/productimages/${product.id}/${product.mainImage}`} />
                //                 )
                //               }
                //               {
                //                 !product.mainImage && (
                //                   <FaPortrait className='icon-sliver' size={120} />
                //                 )
                //               }
                //             </td>
                //             <td>{product.shortName}</td>
                //             <td>{product.brand.name}</td>
                //             <td>{product.category.name}</td>
                //             <td>{product.inventory.amount}</td>
                //             <td>
                //               <LinkContainer to={`/products/${product.id}`}>
                //                 <Button variant="seconary"><FaEdit size={24} /></Button>
                //               </LinkContainer>
                //               <TbMinusVertical />
                //               <Button variant="seconary"
                //                 onClick={() => {
                //                   handleModalShow()
                //                   setTargetProduct(product)
                //                 }}
                //               ><MdDelete size={24} />
                //               </Button>
                //             </td>
                //           </tr>
                //         ))
                //       }
                //     </tbody>
                //   </Table>
                // </Row >
              )
            }
            {
              showModal && <AdminModal
                handleModalClose={handleModalClose}
                deleteHandler={deleteProductHandler}
                title="제품 삭제"
                content={`제품 ${targetProduct.name} 을 삭제하시겠습니까?`}
              />
            }
          </>
        )
      }
    </div>
  )
}

export default ProductListScreen