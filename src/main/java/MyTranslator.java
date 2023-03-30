import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import ai.djl.ndarray.NDList;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;

import java.io.IOException;
import java.nio.file.Path;

public class MyTranslator implements Translator<String, float[]> {

    HuggingFaceTokenizer tokenizer;

    public MyTranslator(Path configuration) {
        try {
            tokenizer = HuggingFaceTokenizer.newInstance(configuration);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public float[] processOutput(TranslatorContext ctx, NDList list) throws Exception {
//        int index = list.getShapes.indexWhere(_.dimension() == 1);
        return list.get(1).toFloatArray();
    }

    @Override
    public NDList processInput(TranslatorContext ctx, String input) throws Exception {
        return tokenizer.encode(input).toNDList(ctx.getNDManager(), true);
    }
}
