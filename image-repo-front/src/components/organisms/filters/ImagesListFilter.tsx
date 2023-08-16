import { styled } from "styled-components";
import { MediaFilter } from "../../../models/mediafilter.model";
import { Form, Formik } from "formik";
import { FlatButton, Input } from "../../atoms";

type ImagesListFilterProps = {
    onApplyFilter: (filterValues: MediaFilter) => void;
}

const StyledImagesListFilter = styled.div`
    form {
        display: grid;
        grid-template-columns: repeat(7, 1fr);
        gap: 12px;
        align-items: center;
        > div {
            grid-column: span 3;
        }
        > button {
            span 1;
            height: 42px;
        }
    }
`;

const ImagesListFilter: React.FunctionComponent<ImagesListFilterProps> = (props) => {

    const { onApplyFilter } = props;

    const initialValues: MediaFilter = {
        name: '',
        description: '',
        tags: []
    }

    const handleSubmit = (values: any): void => {
        onApplyFilter(values as MediaFilter);
    }

    return (
        <StyledImagesListFilter>
            <Formik
                initialValues={initialValues}
                onSubmit={handleSubmit}
            >
                {
                    () => (
                        <Form>
                            <Input name="name" label="Nome" placeholder="Nome" />
                            <Input name="description" label="Descrição" placeholder="Descrição" />
                            <FlatButton type="submit">
                                <p>Pesquisar</p>
                            </FlatButton>
                        </Form>
                    )
                }
            </Formik>
        </StyledImagesListFilter>
    );
}

export default ImagesListFilter;