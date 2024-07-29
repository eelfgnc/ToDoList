import { useSelector } from "react-redux";
import RolePageConstant from "../constants/RolePageConstant";
import ForbiddenPage from "../pages/FobiddenPage";

export const ChecckPermission = (pagePermissions?: string[]): boolean => {
	const userPermissions = useSelector((state: any) => state.user.roles);
	console.log("user permission: ", userPermissions);
	if(pagePermissions === undefined || pagePermissions?.length === 0 || userPermissions.includes(RolePageConstant.ADMIN)) {
		return true;
	}

	return userPermissions.some((t: string) => pagePermissions.includes(t));
}

interface PermissionGateProps {
	children: JSX.Element, 
	pagePermissions?: string[], 
	error?: JSX.Element
}
const PermissionsGate = (props: PermissionGateProps) => {
	if (!ChecckPermission(props.pagePermissions)) {
		return props.error === undefined ? <ForbiddenPage /> : props.error;
	}
	return props.children;
}

export default PermissionsGate;