import './../../styles/shared.css';

const PageContainer: React.FC<{
    children?: React.ReactNode
}> = ({
    children
}) => {
    return (
        <div className="page-content-container">{children}</div>
    )
}

export default PageContainer;