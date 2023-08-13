import { styled } from "styled-components";
import { Form, Formik } from "formik";
import * as Yup from "yup";
import { FlatButton, Input } from "../../atoms";
import UserProfile from "../../../models/interfaces/user-profile.interface";
import { createUser } from "../../../api/services/user.service";
import { toast } from "react-toastify";

type SignUpFormProps = {};

const StyledSignUpForm = styled.div`
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

const SignUpForm: React.FunctionComponent<SignUpFormProps> = (props) => {

    const initialValues: UserProfile = {
        name: "",
        login: "",
        password: "",
        profilePictureId: null,
        profilePicture: null
    };

    const validationSchema = Yup.object({
        name: Yup.string().required("Nome é obrigatório"),
        login: Yup.string().matches(/\s/g, "Não são permitidos login com espaços").required("Login é obrigatório"),
        password: Yup.string().min(6, "Mínimo de 6 caracteres para senha").required("Senha é obrigatório")
    });

    const handleFileUpload = (file: any, setFieldValue: (field: string, value: any) => void) => {

    }

    const onChangeFile = ($event: any) => {

    }

    const handleSubmit = (values: any, { setSubmitting }: any) => {
        setSubmitting(false);
        createUser(values).then(
            () => toast.success("Usuário cadastrado com sucesso!")
        ).catch(
            () => toast.error("Erro ao cadastrar usuário")
        );
    }

    return (
        <StyledSignUpForm>
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
                                Junte-se a nós!
                            </h5>
                            <Input name="name" label="Nome" placeholder="Nome do Usuário" required />
                            <Input name="login" label="Login" placeholder="Login do Usuário" required />
                            <Input name="password" label="Senha" placeholder="Senha do Usuário" type="password" required />
                            <FlatButton type="submit">
                                <p>Concluir</p>
                            </FlatButton>
                        </Form>
                    )
                }
            </Formik>
        </StyledSignUpForm>
    );
}

export default SignUpForm;