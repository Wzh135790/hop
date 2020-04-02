/*! ******************************************************************************
 *
 * Pentaho Data Integration
 *
 * Copyright (C) 2002-2017 by Hitachi Vantara : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.apache.hop.pipeline;

import org.apache.hop.core.plugins.PluginRegistry;
import org.apache.hop.core.plugins.TransformPluginType;
import org.apache.hop.core.variables.VariableSpace;
import org.apache.hop.pipeline.transform.TransformMeta;
import org.apache.hop.pipeline.transform.TransformMetaInterface;
import org.apache.hop.pipeline.transforms.dummy.DummyMeta;

public class PipelinePreviewFactory {
  public static final PipelineMeta generatePreviewPipeline( VariableSpace parent, TransformMetaInterface oneMeta,
                                                            String oneTransformName ) {
    PluginRegistry registry = PluginRegistry.getInstance();

    PipelineMeta previewMeta = new PipelineMeta( parent );
    // The following operation resets the internal variables!
    //
    previewMeta.setName( parent == null ? "Preview pipeline" : parent.toString() );

    // At it to the first transform.
    TransformMeta one = new TransformMeta( registry.getPluginId( TransformPluginType.class, oneMeta ), oneTransformName, oneMeta );
    one.setLocation( 50, 50 );
    previewMeta.addTransform( one );

    DummyMeta twoMeta = new DummyMeta();
    TransformMeta two = new TransformMeta( registry.getPluginId( TransformPluginType.class, twoMeta ), "dummy", twoMeta );
    two.setLocation( 250, 50 );
    previewMeta.addTransform( two );

    PipelineHopMeta hop = new PipelineHopMeta( one, two );
    previewMeta.addPipelineHop( hop );

    return previewMeta;
  }
}