import { Box } from "@mui/material";
import ContentContainer from "./ContentContainer";
import SideBar from "./SideBar";

const PageStyling = {
	display: "flex",
	margin: 0,
	padding: 0,
	width: "100%",
	height: "100%",
	overflow: "hidden",
	backgroundColor: "white"
}

const Page: React.FC<{
	children?: React.ReactNode
}> = ({
	children
}) => {
	return (
		<Box sx={PageStyling}>
			<SideBar/>
			<ContentContainer>{children}</ContentContainer>
		</Box>
	)
}

export default Page;