import PageContainer from "./PageContainer";
import './../../../styles/shared.css';

const Page: React.FC<{
    children?: React.ReactNode
}> = ({
    children
}) => {
    return (
        <div className="page-container">
            <PageContainer>{children}</PageContainer>
        </div>
    )
}

export default Page;