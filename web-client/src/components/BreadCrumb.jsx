import { Link } from "react-router-dom"

const Breadcrumb = ({ root, leafName }) => {
    const breadcrumb = []
    let cur = root
    while (cur != null) {
        breadcrumb.unshift(cur)
        cur = cur.parentCategory
    }

    return <>
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                {
                    breadcrumb.map(c => (
                        <li class="breadcrumb-item">
                            <Link to={`/categories/${c.alias}`}>{c.name}</Link>
                        </li>
                    ))
                }{
                    leafName && (
                        <li class="breadcrumb-item">
                            <Link>{leafName}</Link>
                        </li>
                    )
                }
            </ol>
        </nav>
    </>
}

export default Breadcrumb