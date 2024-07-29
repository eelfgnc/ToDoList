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
	const isLoading = useSelector((str: any) => str.load.isLoading || false);

	return (
		<>
			{isLoading && (
				<div>
					<div
						style={{
							zIndex: 4000,
							display: "flex",
							position: "absolute",
							top: "50%",
							left: "50%",
							marginTop: -42,
							marginLeft: -40,
							background: "#ff5542",
							opacity: 0.4,
							width: "80px",
							height: "80px",
							borderRadius: 40,
						}}
					></div>
					<img
						style={{
							zIndex: 5000,
							display: "flex",
							position: "absolute",
							top: "50%",
							left: "50%",
							marginTop: -75 / 2,
							marginLeft: -75 / 2,
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
