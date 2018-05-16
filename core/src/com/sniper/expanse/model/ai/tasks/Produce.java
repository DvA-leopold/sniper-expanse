package com.sniper.expanse.model.ai.tasks;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.annotation.TaskAttribute;
import com.badlogic.gdx.ai.utils.random.ConstantIntegerDistribution;
import com.badlogic.gdx.ai.utils.random.IntegerDistribution;
import com.badlogic.gdx.graphics.g3d.Model;


public class Produce extends LeafTask<Model> {
    @Override
    public Status execute() {
        final Model model = getObject();
        System.out.println("produce");
        return Status.SUCCEEDED;
    }

    @Override
    protected Task<Model> copyTo(Task<Model> task) {
        return task;
    }


    @TaskAttribute
    public IntegerDistribution times = ConstantIntegerDistribution.ONE;

}
