import { Form, Formik } from "formik";
import { styled } from "styled-components";
import { FlatButton, Input } from "../..";
import * as Yup from "yup";
import { Credentials } from "../../../models/credentials.model";
import { login, setToken } from "../../../api/services/auth.service";
import { toast } from "react-toastify";

type LoginFormProps = {};

const StyledLoginForm = styled.div`
    width: 320px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 12px;
    background-color: white;
    border: 1px solid black;
    border-radius: 8px;
    form {
        width: 100%;
        display: grid;
        grid-template-columns: 1;
        gap: 12px;
        padding: 12px 24px;
        text-align: center;
    }
`;

const LoginForm: React.FunctionComponent<LoginFormProps> = (props) => {

    const initialValues: Credentials = {
        login: "",
        password: ""
    };

    const validationSchema = Yup.object({
        login: Yup.string().matches(/\s/g, "Não são permitidos login com espaços").required("Login é obrigatório"),
        password: Yup.string().min(6, "Mínimo de 6 caracteres para senha").required("Senha é obrigatório")
    });

    const handleSubmit = (values: any, { setSubmitting }: any) => {
        setSubmitting(false);
        login(values).then(
            () => {
                toast.success("Usuário cadastrado com sucesso!");
                setToken(values.token);
            }
        ).catch(
            () => toast.error("Erro ao cadastrar usuário")
        );
    }
    
    return (
        <StyledLoginForm>
            <Formik
                onSubmit={handleSubmit}
                initialValues={initialValues}
                validationSchema={validationSchema}
            >
                {
                    ({values, isSubmitting, setFieldValue}) => (
                        <Form>
                            <h3>
                                Pixel Port
                            </h3>
                            <h5>
                                Acesse agora!
                            </h5>
                            <Input name="login" label="Login" placeholder="Login do Usuário" required />
                            <Input name="password" label="Senha" placeholder="Senha do Usuário" type="password" required />
                            <FlatButton type="submit">
                                <p>Login</p>
                            </FlatButton>
                        </Form>
                    )
                }
            </Formik>
        </StyledLoginForm>
    );
}

export default LoginForm;