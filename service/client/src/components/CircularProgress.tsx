import { useSelector } from "react-redux";
import loadingGif from "../assets/images/loading.gif";
import { store } from "../redux/store";
import { setLoading } from "../redux/slice/LoadingSlice";

export function showLoading() {
	store.dispatch(
		setLoading({
			isLoading: true,
		})
	);
}

export function hideLoading() {
	store.dispatch(
		setLoading({
			isLoading: false,
		})
	);
}

const CircularProgress = () => {
	const isLoading = useSelector((str: any) => str.loading.isLoading || false);

	return (
		<>
			{isLoading && (
				<div>
					<img
						style={{
							zIndex: 5000,
							position: "fixed",
							top: "50%",
							left: "50%",
							transform: "translate(-50%, -50%)",
							width: "250px",
							textAlign: "center",
						}}
						src={loadingGif}
						alt="loading..."
					/>
				</div>
			)}
		</>
	);
};
export default CircularProgress;
