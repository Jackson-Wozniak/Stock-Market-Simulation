import Box from '@mui/material/Box';

const ContentContainer: React.FC<{
    children?: React.ReactNode
}> = ({
    children
}) => {
    return (
        <Box sx={{
            width: "82%", height: "100%", overflowX: "none", overflowY: "auto",
            margin: 0, padding: 0, scrollbarWidth: "thin", 
            scrollbarColor: "rgba(255, 255, 255, 0.2) transparent"
        }}>
            {children}
        </Box>
    )
}

export default ContentContainer;