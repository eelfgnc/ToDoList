import { InitializeUserDTO, UserDTO } from "../dto/UserDTO";
import api from "../config/AxiosConfig";
import { store } from "../redux/store";
import { addNotification } from "../redux/slice/NotificationSlice";
import { Accordion, AccordionActions, AccordionDetails, AccordionSummary, Button, Card, Grid, TextField } from "@mui/material";
import { ExpandMore } from "@mui/icons-material";
import { Field, Form, Formik } from "formik";
import * as yup from "yup";
import { useNavigate } from "react-router-dom";

const UserRegistePage = () => {
    const navigation = useNavigate();
    const validationUser = yup.object({
        firstName: yup
            .string()
            .matches(/^[A-Za-zığüşöçİÜĞÖÇŞ\s]+$/, "Please enter a valid first name")
            .required("First name cannot be blank"),
        lastName: yup
            .string()
            .matches(/^[A-Za-zığüşöçİÜĞÖÇŞ\s]+$/, "Please enter a valid last name")
            .required("Last name cannot be blank"),
        phone: yup
            .string()
            .notRequired(),
        city: yup
            .string()
            .notRequired(),
        email: yup
            .string()
            .email("Please enter a valid email")
            .required("Email cannot be blank"),
        password: yup
            .string()
            .required("Password cannot be blank")
            .min(8, "Password can be minimum 8 characters.")
            .max(15, "Password can be maxsimum 15 characters.")
    });

    const register = (userInfo: UserDTO) =>{
        api.post("auth/register", {
            firstName: userInfo.firstName,
            lastName: userInfo.lastName,
            phone:userInfo.phone,
            city:userInfo.city,
            email:userInfo.email,
            password:userInfo.password
        }).then(() =>{
            store.dispatch(addNotification("Registration is successful."));
            navigation("/login");
        }).catch(() => {
            store.dispatch(addNotification({message:"Registration can't be made with this e-mail address.", type:"error"}));
        })
    }
   
    return(
        <Card>
            <Formik
                initialValues={InitializeUserDTO}
                validationSchema={validationUser}
                onSubmit={(values: UserDTO)=>{
                    register(values);
                }}
            >
                {({errors, touched, setFieldValue, values}) => (
                    <Form>
                        <Accordion defaultExpanded>
                            <AccordionSummary
                                expandIcon={<ExpandMore />}
                                aria-controls="panel2-content"
                                id="panel2-header"
                            >
                                User Info
                            </AccordionSummary>
                            <AccordionDetails>
                                <Grid container spacing={1}>
                                    <Grid item xs={6}>
                                        <Field
                                            as={TextField}
                                            focused
                                            required
                                            fullWidth
                                            variant="outlined"
                                            id="firstName"
                                            name="firstName"
                                            type="text"
                                            label="FirstName"
                                            margin="normal"
                                            value={values.firstName}
                                            error={touched.firstName && Boolean(errors.firstName)}
                                            helperText={touched.firstName && errors.firstName} />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <Field
                                            as={TextField}
                                            required
                                            fullWidth
                                            variant="outlined"
                                            id="lastName"
                                            name="lastName"
                                            type="text"
                                            label="LastName"
                                            margin="normal"
                                            value={values.lastName}
                                            error={touched.lastName && Boolean(errors.lastName)}
                                            helperText={touched.lastName && errors.lastName} />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <Field
                                            as={TextField}
                                            fullWidth
                                            variant="outlined"
                                            id="phone"
                                            name="phone"
                                            type="text"
                                            label="Phone"
                                            margin="normal"
                                            value={values.phone}
                                            error={touched.phone && Boolean(errors.phone)}
                                            helperText={touched.phone && errors.phone} />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <Field
                                            as={TextField}
                                            fullWidth
                                            variant="outlined"
                                            id="city"
                                            name="city"
                                            type="text"
                                            label="City"
                                            margin="normal"
                                            value={values.city}
                                            error={touched.city && Boolean(errors.city)}
                                            helperText={touched.city && errors.city} />
                                    </Grid>                                   
                                </Grid>
                            </AccordionDetails>
                        </Accordion>
                        <Accordion defaultExpanded>
                            <AccordionSummary
                                expandIcon={<ExpandMore />}
                                aria-controls="panel3-content"
                                id="panel3-header"
                            >
                                Account Info
                            </AccordionSummary>
                            <AccordionDetails>
                                <Grid container spacing={1}>
                                    <Grid item xs={6}>
                                        <Field
                                            as={TextField}
                                            required
                                            fullWidth
                                            variant="outlined"
                                            id="email"
                                            name="email"
                                            type="email"
                                            label="Email"
                                            margin="normal"
                                            value={values.email}
                                            error={touched.email && Boolean(errors.email)}
                                            helperText={touched.email && errors.email} />
                                    </Grid>
                                    <Grid item xs={6}>
                                        <Field
                                            as={TextField}
                                            required
                                            fullWidth
                                            variant="outlined"
                                            id="password"
                                            name="password"
                                            type="password"
                                            label="Password"
                                            margin="normal"
                                            value={values.password}
                                            error={touched.password && Boolean(errors.password)}
                                            helperText={touched.password && errors.password} />
                                    </Grid>
                                </Grid>
                            </AccordionDetails>
                            <AccordionActions>
                                <Button
                                    color="error"
                                    variant="contained"
                                    onClick={()=>{
                                        navigation("/login")
                                    }}
                                >
                                    Cancel
                                </Button>
                                <Button
                                    type="submit"
                                    color="primary"
                                    variant="contained"
                                >
                                    Save
                                </Button>
                            </AccordionActions>
                        </Accordion>
                    </Form>
                )}               
            </Formik>
        </Card>
    );
};

export default UserRegistePage;