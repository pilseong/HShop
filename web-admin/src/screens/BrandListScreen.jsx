import React from 'react'
import { Alert } from 'react-bootstrap'
import { useEffect, useState } from 'react'
import axios from 'axios'
import Message from '../util/Message'
import { Link } from 'react-router-dom';
import EnhancedTable from '../components/EnhancedTable';


function BrandListScreen() {

  const [showModal, setShowModal] = useState(false);
  const [targetBrand, setTargetBrand] = useState({})
  const [message, setMessage] = useState("")
  const [currentPage, setCurrentPage] = useState(0)
  const [total, setTotal] = useState(0)
  const [pageSize, setPageSize] = useState(0)
  const [orderBy, setOrderBy] = useState('asc')
  const [sortBy, setSortBy] = useState('name')
  const [keyword, setKeyword] = useState("")
  const [cleared, setCleared] = useState(0)


  // 유저리스트
  const [brands, setBrands] = useState([])
  const [error, setError] = useState({
    code: "",
    message: ""
  })

  // clear 버튼을 눌렀을 때 keyword가 클리어 된 후에 실행되도록 추가
  useEffect(() => {
    if (cleared > 0)
      fetchBrands(0, 'asc', 'name')
  }, [cleared])

  useEffect(() => {
    fetchBrands(0)
  }, [])

  const fetchBrands = async (page = 0, order = 'asc', sort = 'name', size = 10) => {
    try {
      const { data } = await axios.get(
        `http://localhost:8080/catalog-service/api/brands`, {
        params: {
          query: keyword,
          pageNo: page,
          pageSize: size,
          sortBy: sort,
          orderBy: order
        }
      }, {
        headers: {
          "Content-Type": "application/json"
        },
        withCredentials: true
      });
      console.log(data)
      setBrands(data.content)
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
        `/api/brands/export/csv?pageNo=${currentPage}&pageSize=10&sortBy=${sortBy}&orderBy=${orderBy}`, {
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

  const deleteBrandHandler = async () => {
    try {
      await axios.delete(`/api/brands/${targetBrand.id}`)
      await fetchBrands(currentPage, orderBy, sortBy)

      showMessage(`user with ${targetBrand.name} has been deleted successfully`)

    } catch (error) {
      console.log(error)
      setError(error.response.data)
    }
    setShowModal(false);
  }

  const handleModalShow = () => setShowModal(true);


  // 수정 실행 메소드
  const submitEdition = async (user) => {
    try {
      // 데이터 생성
      const formData = new FormData();
      formData.append("command", new Blob([JSON.stringify(user)], {
        type: "application/json",
      }));

      const result = await axios.post('/api/brands/edit', formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        }
      })
      console.log(result)
      showMessage(`user with ${user.name} has been upated successfully`)
      await fetchBrands(currentPage, orderBy, sortBy)
    } catch (error) {
      console.log(error)
      setError(error.response.data)
      return
    }
  }

  const handleActiveToggle = async (user) => {

    // 서버에 변경, 패스워드 공백은 검증로직 예외
    await submitEdition({
      ...user,
      password: '',
      status: user.status === 'ACTIVE' ? 'INACTIVE' : 'ACTIVE',
      roles: user.roles.map(r => r.id)
    })

    //     // 목록 업데이트
    //     // let updatedBrands = brands.filter(u => u.id !== user.id)
    //     // updatedBrands.push({ ...user, enabled: !user.enabled })

    //     // setBrands(updatedBrands)
  }

  const navigatePage = (page, size = pageSize) => {
    fetchBrands(page, orderBy, sortBy, size)
  }

  const showMessage = (msg) => {
    setMessage(msg)

    setTimeout(() => {
      setMessage("");
    }, 3000);

  }
  return (
    <div className='text-gray-600'>
      <h3 className='my-6 text-4xl'>브랜드 목록</h3>
      <div className="flex gap-2">
        <input type="text"
          onKeyDown={e => {
            if (e.key === 'Enter') {
              fetchBrands(0, 'asc', 'name')
            }
          }}
          onChange={e => {
            setKeyword(e.target.value)
          }}
          value={keyword}
          placeholder="Enter keyword" className="bg-gray-100 p-2" />
        <button className='btn'
          onClick={() => fetchBrands(0, 'asc', 'name')}
        >Search</button>
        <button className='btn'
          onClick={() => {
            setKeyword('')
            setCleared(cleared + 1)
          }}
        >Clear</button>
        <Link className='ml-auto' to="/brands/new">
          <button className='btn'>Create Brand</button>
        </Link>
      </div>
      {
        error?.code && (
          <Message variant='danger' className='mt-2'>
            <div className="text-xs text-gray-300">
              {
                error.message
                  .split(',')
                  .map(err => <><strong>{err}</strong><br /></>)
              }
            </div>
          </Message>
        )
      }
      {
        message.length > 0 && (
          <Alert variant="info">{message}</Alert>
        )
      }
      {
        brands.length === 0 ? (
          <div className='text-center mt-5'>
            <h4>검색된 브랜드가 없습니다.</h4>
          </div>
        ) : (
          <div className='mt-10'>
            <EnhancedTable
              handleModalShow={handleModalShow}
              data={brands}
              navigatePage={navigatePage}
              total={total}
              page={currentPage}
              size={pageSize || 10}
              setTarget={setTargetBrand}
              headCells={[
                {
                  id: 'id',
                  type: 'string',
                  label: 'Brand Id',
                },
                {
                  id: 'logo',
                  type: 'image',
                  label: 'Logo',
                  path: 'http://localhost:8080/catalog-service/photos/brandlogos/'
                },
                {
                  id: 'name',
                  type: 'string',
                  label: 'Brand Name',
                },
                {
                  id: 'categories',
                  type: 'string',
                  label: 'Categories',
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
        )
      }
      {
        showModal && <AdminModal
          handleModalClose={handleModalClose}
          deleteHandler={deleteBrandHandler}
          title="브랜드 삭제"
          content={`브랜드 ${targetBrand.name} 을 삭제하시겠습니까?`}
        />
      }
    </div>
  )
}

export default BrandListScreen