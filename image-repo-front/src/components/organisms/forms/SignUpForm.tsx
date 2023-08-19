import { styled } from "styled-components";
import { Form, Formik } from "formik";
import * as Yup from "yup";
import { FileInput, FlatButton, Input } from "../../atoms";
import UserProfile from "../../../models/interfaces/user-profile.interface";
import { createUser } from "../../../api/services/user.service";
import { toast } from "react-toastify";
import { uploadFile } from "../../../api/services/file.service";
import MediaFile from "../../../models/mediafile.model";
import { NavigateFunction, useNavigate } from "react-router-dom";
import { useSplashScreen } from "../../../contexts/SplashScreenProvider";

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

    const navigate: NavigateFunction = useNavigate();

    const { start, stop } = useSplashScreen();

    const initialValues: UserProfile = {
        name: "",
        login: "",
        password: "",
        profilePictureId: null,
        profilePicture: null
    };

    const validationSchema = Yup.object({
        name: Yup.string().required("Nome é obrigatório"),
        login: Yup.string().matches(/^\S+$/, "Não são permitidos login com espaços").required("Login é obrigatório"),
        password: Yup.string().min(6, "Mínimo de 6 caracteres para senha").required("Senha é obrigatório")
    });

    const onChangeFile = ($event: any, setFieldValue: (field: string, value: any) => void) => {
        start();
        uploadFile($event.currentTarget.files[0])
            .then(
                response => {
                    const data = response.data;
                    setFieldValue("profilePictureId", data?.id);
                    setFieldValue("profilePicture", data);
                    toast.success("Imagem cadastrada com sucesso");
                    stop();
                }
            ).catch(
                error => {
                    toast.error(error.message);
                    stop();
                }
            )
    }

    const handleSubmit = (values: any) => {
        start();
        createUser(values).then(
            () => {
                toast.success("Usuário cadastrado com sucesso!");
                navigate("/login");
                stop();
            }
        ).catch(
            error => {
                toast.error(error.message);
                stop();
            }
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
                    ({values, setFieldValue}) => (
                        <Form>
                            <h3>
                                Pixel Port
                            </h3>
                            <h5>
                                Junte-se a nós!
                            </h5>
                            <FileInput name="file" src={values.profilePicture?.base64} placeholder="Foto de Perfil" onChangeValue={$event => onChangeFile($event, setFieldValue)} />
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