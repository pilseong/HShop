import { Container } from 'react-bootstrap';
import { Outlet } from 'react-router-dom';
import Footer from './components/Footer';
import Header from './components/Header';
import { useGetSettingsMutation } from './slices/settingsApiSlice'
import { useDispatch } from 'react-redux';
import { setSettings } from './slices/settingsSlice';
import { useEffect, useState } from 'react';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css'

function App() {

  // 여기서는 매번 실행하지만 실제로는 캐시에서 읽어오거나
  // 주기적으로 읽어오는 것이 낫다.
  // const { data: settings, isLoading: isSettingsLoading, error: settingsError } = useGetSettingsQuery()

  const [getSettings, { isLoading }] = useGetSettingsMutation()
  const dispatch = useDispatch()

  const transformToSettingObj = (originalSettings) => {
    return originalSettings.reduce((result, obj) => {
      return result = { ...result, [obj.key]: obj }
    }, {})
  }

  useEffect(() => {

    const fetchSettings = async () => {
      const { data } = await getSettings()
      if (data?.content) {
        console.log(data.content)
        const site_name = data.content.filter(s => s.key === "SITE_NAME")
        document.title = site_name[0].value

        const settingsObj = transformToSettingObj(data.content)
        dispatch(setSettings({ ...settingsObj }))
      }
    }

    fetchSettings()
  }, [])

  return (
    <div>
      <Header />
      <main className="py-3">
        <Container>
          <Outlet />
        </Container>
      </main>
      <Footer />
      <ToastContainer />
    </div>
  );
}

export default App;
